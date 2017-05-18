package balogh.zoltan.todo.ui.tododetails;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import balogh.zoltan.todo.R;
import balogh.zoltan.todo.TodoApplication;
import balogh.zoltan.todo.model.Todo;
import balogh.zoltan.todo.ui.login.LoginActivity;

public class TodoDetailsActivity extends AppCompatActivity implements TodoDetailsScreen {

    @Inject
    TodoDetailsPresenter todoDetailsPresenter;
    MenuItem check;
    private Todo todo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TodoApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        todoDetailsPresenter.attachScreen(this);
        Intent intent = getIntent();
        todoDetailsPresenter.loadTodo(intent.getLongExtra("id", 0));
    }

    @Override
    protected void onStop() {
        super.onStop();
        todoDetailsPresenter.detachScreen();
    }

    @Override
    public void showTodo(Todo todo) {
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        this.todo = todo;
        ab.setTitle(todo.getTitle());
        TextView content = (TextView) findViewById(R.id.content);
        content.setText(todo.getContent());
        if (check != null) {
            if (todo.isDone()) {
                check.setIcon(R.drawable.ic_check_box_white_24dp);
            } else {
                check.setIcon(R.drawable.ic_check_box_outline_blank_white_24dp);
            }
        }
    }

    @Override
    public void showSucces() {
        if (todo.isDone()) {
            check.setIcon(R.drawable.ic_check_box_white_24dp);
        } else {
            check.setIcon(R.drawable.ic_check_box_outline_blank_white_24dp);
        }
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

    private void checkTodo(Todo todo, boolean isDone) {
        todoDetailsPresenter.checkTodo(todo, isDone);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_details, menu);
        check = menu.findItem(R.id.check);
        if (todo != null) {
            if (todo.isDone()) {
                check.setIcon(R.drawable.ic_check_box_white_24dp);
            } else {
                check.setIcon(R.drawable.ic_check_box_outline_blank_white_24dp);
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.check:
                checkTodo(todo, !todo.isDone());
                break;
            case R.id.logout:
                todoDetailsPresenter.logout();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
