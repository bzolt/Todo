package balogh.zoltan.todo.interactor.user.events;

public class DeleteUserEvent {
    private int code;
    private Throwable throwable;

    public DeleteUserEvent() {
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