package balogh.zoltan.todo.interactor.todo.events;

import java.util.List;

import balogh.zoltan.todo.model.Todo;

public class GetTodosEvent {
    private int code;
    private List<Todo> todos;
    private Throwable throwable;

    public GetTodosEvent() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
}