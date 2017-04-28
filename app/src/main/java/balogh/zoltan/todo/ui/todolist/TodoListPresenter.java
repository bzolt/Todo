package balogh.zoltan.todo.ui.todolist;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import balogh.zoltan.todo.interactor.todo.TodoInteractor;
import balogh.zoltan.todo.interactor.todo.events.DeleteTodoEvent;
import balogh.zoltan.todo.interactor.todo.events.GetTodosEvent;
import balogh.zoltan.todo.interactor.todo.events.SaveTodoEvent;
import balogh.zoltan.todo.model.Todo;
import balogh.zoltan.todo.ui.Presenter;
import de.greenrobot.event.EventBus;

import static balogh.zoltan.todo.TodoApplication.injector;

public class TodoListPresenter extends Presenter<TodoListScreen> {
    @Inject
    TodoInteractor todoInteractor;

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

    public void checkTodo(final Todo todo) {
        todo.setDone(true);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                todoInteractor.saveTodo(todo);
            }
        });
    }

    public void uncheckTodo(final Todo todo) {
        todo.setDone(false);
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

    public void onEventMainThread(GetTodosEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showError("Could not load todos from database.");
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
                screen.showError("Could not delete todo from database.");
            }
        } else {
            if (screen != null) {
                screen.showSucces();
            }
        }
    }

    public void onEventMainThread(SaveTodoEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showError("Could not save todo to database.");
            }
        } else {
            if (screen != null) {
                screen.showSucces();
            }
        }
    }
}