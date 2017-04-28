package balogh.zoltan.todo.interactor.todo.events;

import balogh.zoltan.todo.model.Todo;

public class GetTodoEvent {
    private int code;
    private Todo todo;
    private Throwable throwable;

    public GetTodoEvent() {
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

    public Todo getTodo() {
        return todo;
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
    }
}