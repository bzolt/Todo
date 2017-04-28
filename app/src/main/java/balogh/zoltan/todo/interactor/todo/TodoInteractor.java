package balogh.zoltan.todo.interactor.todo;

import java.util.List;

import javax.inject.Inject;

import balogh.zoltan.todo.TodoApplication;
import balogh.zoltan.todo.interactor.todo.events.DeleteTodoEvent;
import balogh.zoltan.todo.interactor.todo.events.GetTodoEvent;
import balogh.zoltan.todo.interactor.todo.events.GetTodosEvent;
import balogh.zoltan.todo.interactor.todo.events.SaveTodoEvent;
import balogh.zoltan.todo.model.Todo;
import balogh.zoltan.todo.repository.Repository;
import de.greenrobot.event.EventBus;

public class TodoInteractor {
    @Inject
    Repository repository;
    @Inject
    EventBus bus;

    public TodoInteractor() {
        TodoApplication.injector.inject(this);
    }

    public void getTodo(long id) {
        GetTodoEvent event = new GetTodoEvent();
        try {
            Todo todo = repository.getTodo(id);
            event.setTodo(todo);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void getTodos() {
        GetTodosEvent event = new GetTodosEvent();
        try {
            List<Todo> todos = repository.getTodos();
            event.setTodos(todos);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void saveTodo(Todo todo) {
        SaveTodoEvent event = new SaveTodoEvent();
        try {
            repository.saveTodo(todo);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void deleteTodo(Todo todo) {
        DeleteTodoEvent event = new DeleteTodoEvent();
        try {
            repository.deleteTodo(todo);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }
}
