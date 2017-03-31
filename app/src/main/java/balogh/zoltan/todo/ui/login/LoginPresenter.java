package balogh.zoltan.todo.ui.login;

import balogh.zoltan.todo.ui.Presenter;

public class LoginPresenter extends Presenter<LoginScreen> {

    public LoginPresenter() {
    }

    @Override
    public void attachScreen(LoginScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void login(String username, String password) {
        // TODO
        if (true)
        {
            screen.loginSuccess();
        }
        else
        {
            screen.loginError("Error");
        }
    }
}