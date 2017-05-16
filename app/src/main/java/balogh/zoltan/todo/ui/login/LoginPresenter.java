package balogh.zoltan.todo.ui.login;

import android.app.Activity;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import balogh.zoltan.todo.interactor.user.UserInteractor;
import balogh.zoltan.todo.interactor.user.events.SaveUserEvent;
import balogh.zoltan.todo.model.User;
import balogh.zoltan.todo.ui.Presenter;
import de.greenrobot.event.EventBus;

import static balogh.zoltan.todo.TodoApplication.injector;

public class LoginPresenter extends Presenter<LoginScreen> {
    @Inject
    UserInteractor userInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    public LoginPresenter() {
    }

    @Override
    public void attachScreen(LoginScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen() {
        bus.unregister(this);
        super.detachScreen();
    }

    public void login(final String username, final String password, final Activity thisActivity) {
        // TODO hálózati kommunikáció
        // TODO
        if (true) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    User user = new User(null, username, password);
                    userInteractor.saveUser(user);
                }
            });
        } else {
            screen.showError("Error");
        }
    }

    public void onEventMainThread(SaveUserEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showError("Could not save todo to database.");
            }
        } else {
            if (screen != null) {
                screen.showSuccess();
            }
        }
    }
}