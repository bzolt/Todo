package balogh.zoltan.todo.ui.todoedit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import javax.inject.Inject;

import balogh.zoltan.todo.R;
import balogh.zoltan.todo.TodoApplication;

public class TodoEditActivity extends AppCompatActivity implements TodoEditScreen {

    @Inject
    TodoEditPresenter todoEditPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TodoApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        todoEditPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        todoEditPresenter.detachScreen();
    }

    @Override
    public void fillTodo(String todo) {
        // TODO
    }

    @Override
    public void editSuccess() {
        // TODO
    }

    @Override
    public void editError(String text) {
        // TODO
    }
}
