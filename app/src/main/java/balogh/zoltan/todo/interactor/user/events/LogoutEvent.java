package balogh.zoltan.todo.interactor.user.events;

public class LogoutEvent {
    private int code;
    private Throwable throwable;

    public LogoutEvent() {
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