package balogh.zoltan.todo.ui.login;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import balogh.zoltan.todo.R;
import balogh.zoltan.todo.interactor.user.UserInteractor;
import balogh.zoltan.todo.interactor.user.events.GetUserEvent;
import balogh.zoltan.todo.interactor.user.events.LoginEvent;
import balogh.zoltan.todo.interactor.user.events.SaveUserEvent;
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

    public void login(final String username, final String password) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                userInteractor.login(username, password);
            }
        });
    }

    public void checkLogin() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                userInteractor.getUser();
            }
        });
    }

    public void onEventMainThread(SaveUserEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showError(R.string.db_error);
            }
        } else {
            if (screen != null) {
                screen.showSuccess();
            }
        }
    }

    public void onEventMainThread(final LoginEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showError(R.string.network_error);
            }
        } else {
            if (screen != null) {
                if (event.isSuccess()) {
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            userInteractor.saveUser(event.getUser());
                        }
                    });
                } else {
                    screen.showError(R.string.wrong_credentials);
                }
            }
        }
    }

    public void onEventMainThread(final GetUserEvent event) {
        if (event.getThrowable() == null) {
            if (screen != null) {
                screen.showSuccess();
            }
        }
    }
}