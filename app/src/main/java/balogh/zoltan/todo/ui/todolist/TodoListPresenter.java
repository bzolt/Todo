package balogh.zoltan.todo.ui.todolist;

import android.accounts.NetworkErrorException;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import balogh.zoltan.todo.R;
import balogh.zoltan.todo.interactor.todo.TodoInteractor;
import balogh.zoltan.todo.interactor.todo.events.DeleteTodoEvent;
import balogh.zoltan.todo.interactor.todo.events.GetTodosEvent;
import balogh.zoltan.todo.interactor.todo.events.SaveTodoEvent;
import balogh.zoltan.todo.interactor.user.UserInteractor;
import balogh.zoltan.todo.interactor.user.events.LogoutEvent;
import balogh.zoltan.todo.model.Todo;
import balogh.zoltan.todo.ui.Presenter;
import de.greenrobot.event.EventBus;

import static balogh.zoltan.todo.TodoApplication.injector;

public class TodoListPresenter extends Presenter<TodoListScreen> {
    @Inject
    TodoInteractor todoInteractor;

    @Inject
    UserInteractor userInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    public TodoListPresenter() {
    }

    @Override
    public void attachScreen(TodoListScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen() {
        bus.unregister(this);
        super.detachScreen();
    }

    public void loadTodoList() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                todoInteractor.getTodos();
            }
        });
    }

    public void checkTodo(final Todo todo, final boolean isDone) {
        todo.setDone(isDone);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                todoInteractor.saveTodo(todo);
            }
        });
    }

    public void deleteTodo(final Todo todo) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                todoInteractor.deleteTodo(todo);
            }
        });
    }

    public void logout() {
        userInteractor.logout();
    }

    public void onEventMainThread(GetTodosEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                if (event.getThrowable() instanceof NetworkErrorException) {
                    screen.showError(R.string.network_error);
                } else {
                    screen.showError(R.string.db_error);
                }
            }
        } else {
            if (screen != null) {
                screen.showTodoList(event.getTodos());
            }
        }
    }

    public void onEventMainThread(DeleteTodoEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                if (event.getThrowable() instanceof NetworkErrorException) {
                    screen.showError(R.string.network_error);
                } else {
                    screen.showError(R.string.db_error);
                }
            }
        } else {
            if (screen != null) {
                screen.showDelete(event.getTodo());
            }
        }
    }

    public void onEventMainThread(SaveTodoEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                if (event.getThrowable() instanceof NetworkErrorException) {
                    screen.showError(R.string.network_error);
                } else {
                    screen.showError(R.string.db_error);
                }

            }
        } else {
            if (screen != null) {
                screen.showSucces();
            }
        }
    }

    public void onEventMainThread(LogoutEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showError(R.string.logout_fail);
            }
        } else {
            if (screen != null) {
                screen.logoutSuccess();
            }
        }
    }
}