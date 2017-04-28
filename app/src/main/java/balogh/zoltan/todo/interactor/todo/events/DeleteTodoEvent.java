package balogh.zoltan.todo.interactor.todo.events;

public class DeleteTodoEvent {
    private int code;
    private Throwable throwable;

    public DeleteTodoEvent() {
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

}