package balogh.zoltan.todo.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import balogh.zoltan.todo.R;
import balogh.zoltan.todo.TodoApplication;
import balogh.zoltan.todo.ui.todolist.TodoListActivity;
import io.fabric.sdk.android.Fabric;

public class LoginActivity extends AppCompatActivity implements LoginScreen {

    @Inject
    LoginPresenter loginPresenter;

    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        TodoApplication.injector.inject(this);

        // Obtain the shared Tracker instance.
        TodoApplication application = (TodoApplication) getApplication();
        mTracker = application.getDefaultTracker();

        setContentView(R.layout.activity_login);

        Button btn = (Button) findViewById(R.id.loginButton);
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTracker.send(new HitBuilders.EventBuilder()
                        .setCategory("Action")
                        .setAction("Login")
                        .build());
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
    protected void onResume() {
        super.onResume();
        Log.i("LoginActivity", "Setting screen name: LoginActivity");
        mTracker.setScreenName("LoginActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
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
