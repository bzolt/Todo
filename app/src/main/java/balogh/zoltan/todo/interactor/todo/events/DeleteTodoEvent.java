package balogh.zoltan.todo.interactor.todo.events;

import balogh.zoltan.todo.model.Todo;

public class DeleteTodoEvent {
    private int code;
    private Todo todo;
    private Throwable throwable;

    public DeleteTodoEvent() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Todo getTodo() {
        return todo;
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

}