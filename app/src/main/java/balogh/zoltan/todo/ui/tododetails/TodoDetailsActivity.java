package balogh.zoltan.todo.ui.tododetails;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import balogh.zoltan.todo.R;
import balogh.zoltan.todo.TodoApplication;
import balogh.zoltan.todo.model.Todo;

public class TodoDetailsActivity extends AppCompatActivity implements TodoDetailsScreen {

    @Inject
    TodoDetailsPresenter todoDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TodoApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        todoDetailsPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        todoDetailsPresenter.detachScreen();
    }

    @Override
    public void showTodo(Todo todo)
    {
        // TODO
    }

    @Override
    public void showSucces() {

    }

    @Override
    public void showError(String text) {

    }
}
