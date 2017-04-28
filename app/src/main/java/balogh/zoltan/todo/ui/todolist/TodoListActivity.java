package balogh.zoltan.todo.ui.todolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import javax.inject.Inject;

import balogh.zoltan.todo.R;
import balogh.zoltan.todo.TodoApplication;
import balogh.zoltan.todo.model.Todo;

public class TodoListActivity extends AppCompatActivity implements TodoListScreen {

    @Inject
    TodoListPresenter todoListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TodoApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        todoListPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        todoListPresenter.detachScreen();
    }


    @Override
    public void showTodoList(List<Todo> todos) {
        // TODO
    }

    @Override
    public void showSucces() {
        // TODO
    }

    @Override
    public void showError(String text) {
        // TODO
    }
}
