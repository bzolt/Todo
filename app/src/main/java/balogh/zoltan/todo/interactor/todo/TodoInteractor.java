package balogh.zoltan.todo.interactor.todo;

import android.accounts.NetworkErrorException;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import balogh.zoltan.todo.TodoApplication;
import balogh.zoltan.todo.interactor.todo.events.DeleteTodoEvent;
import balogh.zoltan.todo.interactor.todo.events.GetTodoEvent;
import balogh.zoltan.todo.interactor.todo.events.GetTodosEvent;
import balogh.zoltan.todo.interactor.todo.events.SaveTodoEvent;
import balogh.zoltan.todo.model.Todo;
import balogh.zoltan.todo.network.api.TodoApi;
import balogh.zoltan.todo.repository.Repository;
import de.greenrobot.event.EventBus;
import retrofit2.Response;

public class TodoInteractor {
    @Inject
    Repository repository;
    @Inject
    EventBus bus;
    @Inject
    TodoApi todoApi;

    public TodoInteractor() {
        TodoApplication.injector.inject(this);
    }

    public void getTodo(long id) {
        GetTodoEvent event = new GetTodoEvent();
        try {
            Todo todo = repository.getTodo(id);
            if (todo == null) {
                Response<balogh.zoltan.todo.network.model.Todo> response = todoApi.todoIdGet(id).execute();
                if (response.isSuccess()) {
                    balogh.zoltan.todo.network.model.Todo netTodo = response.body();
                    todo = new Todo(netTodo.getId(), netTodo.getTitle(), netTodo.getContent(), netTodo.getDone());
                    repository.saveTodo(todo);
                } else {
                    throw new NetworkErrorException();
                }
            }
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
            if (todos == null || todos.size() == 0) {
                todos = new ArrayList<Todo>();
                Response<List<balogh.zoltan.todo.network.model.Todo>> response = todoApi.todoGet().execute();
                if (response.isSuccess()) {
                    for (balogh.zoltan.todo.network.model.Todo netTodo : response.body()) {
                        Todo todo = new Todo(netTodo.getId(), netTodo.getTitle(), netTodo.getContent(), netTodo.getDone());
                        todos.add(todo);
                        repository.saveTodo(todo);
                    }
                } else {
                    throw new NetworkErrorException();
                }
            }
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
            balogh.zoltan.todo.network.model.Todo netTodo = new balogh.zoltan.todo.network.model.Todo();
            netTodo.setId(todo.getId());
            netTodo.setTitle(todo.getTitle());
            netTodo.setContent(todo.getContent());
            netTodo.setDone(todo.isDone());
            Response<Void> response = todoApi.todoPost(netTodo).execute();
            if (!response.isSuccess()) {
                repository.deleteTodo(todo);
                throw new NetworkErrorException();
            }
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
            Response<Void> response = todoApi.todoIdDelete(todo.getId()).execute();
            if (!response.isSuccess()) {
                repository.saveTodo(todo);
                throw new NetworkErrorException();
            }
            event.setTodo(todo);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }
}
