package balogh.zoltan.todo.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import balogh.zoltan.todo.R;
import balogh.zoltan.todo.TodoApplication;
import balogh.zoltan.todo.ui.todolist.TodoListActivity;

public class LoginActivity extends AppCompatActivity implements LoginScreen {

    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TodoApplication.injector.inject(this);

        setContentView(R.layout.activity_login);

        Button btn = (Button) findViewById(R.id.loginButton);
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.length() == 0 || password.length() == 0) {
                    Toast.makeText(v.getContext(), R.string.fill_fields, Toast.LENGTH_SHORT).show();
                } else {
                    loginPresenter.login(username.getText().toString(), password.getText().toString());
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        loginPresenter.attachScreen(this);
        loginPresenter.checkLogin();
    }

    @Override
    protected void onStop() {
        super.onStop();
        loginPresenter.detachScreen();
    }

    @Override
    public void showSuccess() {
        Intent intent = new Intent(this, TodoListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void showError(int code) {
        Toast.makeText(this, getString(code), Toast.LENGTH_SHORT).show();
    }
}
