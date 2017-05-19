package balogh.zoltan.todo.ui.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import balogh.zoltan.todo.R;
import balogh.zoltan.todo.TodoApplication;
import balogh.zoltan.todo.model.Todo;
import balogh.zoltan.todo.ui.login.LoginActivity;
import balogh.zoltan.todo.ui.tododetails.TodoDetailsActivity;
import balogh.zoltan.todo.ui.todoedit.TodoEditActivity;
import io.fabric.sdk.android.Fabric;

public class TodoListActivity extends AppCompatActivity implements TodoListScreen {

    @Inject
    TodoListPresenter todoListPresenter;

    private List<Todo> todos = new ArrayList<Todo>();
    private TodoAdapter adapter;
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_list);

        FloatingActionButton newTodo = (FloatingActionButton) findViewById(R.id.newFab);
        newTodo.setOnClickListener(new TodoEditListener());

        GridView gridview = (GridView) findViewById(R.id.gridView);
        adapter = new TodoAdapter(todos, new TodoCheckListener(), new TodoDetailsListener(), new TodoEditListener(), new TodoDeleteListener());
        gridview.setAdapter(adapter);

        TodoApplication.injector.inject(this);
        // Obtain the shared Tracker instance.
        TodoApplication application = (TodoApplication) getApplication();
        mTracker = application.getDefaultTracker();
    }

    @Override
    protected void onStart() {
        super.onStart();
        todoListPresenter.attachScreen(this);
        todoListPresenter.loadTodoList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("TodoListActivity", "Setting screen name: TodoListActivity");
        mTracker.setScreenName("TodoListActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    protected void onStop() {
        super.onStop();
        todoListPresenter.detachScreen();
    }

    @Override
    public void showTodoList(List<Todo> todos) {
        this.todos.clear();
        this.todos.addAll(todos);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showSucces() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showDelete(Todo todo) {
        todos.remove(todo);
        adapter.notifyDataSetChanged();
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
        todoListPresenter.checkTodo(todo, isDone);
    }

    private void todoDetails(Long id) {
        Intent intent = new Intent(this, TodoDetailsActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    private void todoEdit(Long id) {
        Intent intent = new Intent(this, TodoEditActivity.class);
        if (id != null) {
            intent.putExtra("id", id);
        }
        startActivity(intent);
    }

    private void deleteTodo(Todo todo) {
        todoListPresenter.deleteTodo(todo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                mTracker.send(new HitBuilders.EventBuilder()
                        .setCategory("Action")
                        .setAction("Logout")
                        .build());
                todoListPresenter.logout();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private class TodoCheckListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            mTracker.send(new HitBuilders.EventBuilder()
                    .setCategory("Action")
                    .setAction("Check")
                    .build());
            Todo todo = (Todo) buttonView.getTag();
            checkTodo(todo, isChecked);
        }
    }

    private class TodoDetailsListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            mTracker.send(new HitBuilders.EventBuilder()
                    .setCategory("Action")
                    .setAction("Details")
                    .build());
            todoDetails((Long) v.getTag());
        }
    }

    private class TodoEditListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            mTracker.send(new HitBuilders.EventBuilder()
                    .setCategory("Action")
                    .setAction("Edit")
                    .build());
            todoEdit((Long) v.getTag());
        }
    }

    private class TodoDeleteListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            mTracker.send(new HitBuilders.EventBuilder()
                    .setCategory("Action")
                    .setAction("Delete")
                    .build());
            deleteTodo((Todo) v.getTag());
        }
    }
}
