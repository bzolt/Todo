package balogh.zoltan.todo.ui.todoedit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import balogh.zoltan.todo.R;
import balogh.zoltan.todo.TodoApplication;
import balogh.zoltan.todo.model.Todo;
import balogh.zoltan.todo.ui.login.LoginActivity;
import io.fabric.sdk.android.Fabric;

public class TodoEditActivity extends AppCompatActivity implements TodoEditScreen {

    @Inject
    TodoEditPresenter todoEditPresenter;

    private Todo todo;
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_edit);

        TodoApplication.injector.inject(this);
        // Obtain the shared Tracker instance.
        TodoApplication application = (TodoApplication) getApplication();
        mTracker = application.getDefaultTracker();
    }

    @Override
    protected void onStart() {
        super.onStart();
        todoEditPresenter.attachScreen(this);
        Intent intent = getIntent();

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        if (intent.hasExtra("id")) {
            ab.setTitle(R.string.edit_todo);
            todoEditPresenter.loadTodo(intent.getLongExtra("id", 0));
        } else {
            ab.setTitle(R.string.new_todo);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("TodoEditActivity", "Setting screen name: TodoEditActivity");
        mTracker.setScreenName("TodoEditActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    protected void onStop() {
        super.onStop();
        todoEditPresenter.detachScreen();
    }

    @Override
    public void fillTodo(Todo todo) {
        this.todo = todo;
        EditText title = (EditText) findViewById(R.id.title);
        title.setText(todo.getTitle());
        EditText content = (EditText) findViewById(R.id.content);
        content.setText(todo.getContent());
    }

    @Override
    public void showSuccess() {
        Toast.makeText(this, getString(R.string.save_success), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showError(int code) {
        Toast.makeText(this, getString(code), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void logoutSuccess() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                mTracker.send(new HitBuilders.EventBuilder()
                        .setCategory("Action")
                        .setAction("Save")
                        .build());
                EditText title = (EditText) findViewById(R.id.title);
                EditText content = (EditText) findViewById(R.id.content);
                if (title.length() == 0 || content.length() == 0) {
                    Toast.makeText(this, getString(R.string.fill_fields), Toast.LENGTH_SHORT).show();
                } else {
                    if (todo == null) {
                        todo = new Todo();
                    }
                    todo.setTitle(title.getText().toString());
                    todo.setContent(content.getText().toString());
                    todoEditPresenter.saveTodo(todo);
                }
                break;
            case R.id.logout:
                mTracker.send(new HitBuilders.EventBuilder()
                        .setCategory("Action")
                        .setAction("Logout")
                        .build());
                todoEditPresenter.logout();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
