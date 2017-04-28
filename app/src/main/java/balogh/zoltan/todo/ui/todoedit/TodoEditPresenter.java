package balogh.zoltan.todo.ui.todoedit;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import balogh.zoltan.todo.interactor.todo.TodoInteractor;
import balogh.zoltan.todo.interactor.todo.events.GetTodoEvent;
import balogh.zoltan.todo.interactor.todo.events.SaveTodoEvent;
import balogh.zoltan.todo.model.Todo;
import balogh.zoltan.todo.ui.Presenter;
import de.greenrobot.event.EventBus;

import static balogh.zoltan.todo.TodoApplication.injector;

public class TodoEditPresenter extends Presenter<TodoEditScreen> {
    @Inject
    TodoInteractor todoInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    public TodoEditPresenter() {
    }

    @Override
    public void attachScreen(TodoEditScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen() {
        bus.unregister(this);
        super.detachScreen();
    }

    public void saveTodo(final Todo todo)
    {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                todoInteractor.saveTodo(todo);
            }
        });
    }

    public void loadTodo(final long id)
    {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                todoInteractor.getTodo(id);
            }
        });
    }

    public void onEventMainThread(SaveTodoEvent event) {
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

    public void onEventMainThread(GetTodoEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showError("Could not load todo from database.");
            }
        } else {
            if (screen != null) {
                screen.fillTodo(event.getTodo());
            }
        }
    }
}