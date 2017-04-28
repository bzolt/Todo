package balogh.zoltan.todo.interactor.user;

import javax.inject.Inject;

import balogh.zoltan.todo.TodoApplication;
import balogh.zoltan.todo.interactor.user.events.DeleteUserEvent;
import balogh.zoltan.todo.interactor.user.events.GetUserEvent;
import balogh.zoltan.todo.interactor.user.events.SaveUserEvent;
import balogh.zoltan.todo.model.User;
import balogh.zoltan.todo.repository.Repository;
import de.greenrobot.event.EventBus;

public class UserInteractor {
    @Inject
    Repository repository;
    @Inject
    EventBus bus;

    public UserInteractor() {
        TodoApplication.injector.inject(this);
    }

    public void getUser() {
        GetUserEvent event = new GetUserEvent();
        try {
            User user = repository.getUser();
            event.setUser(user);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void saveUser(User user) {
        SaveUserEvent event = new SaveUserEvent();
        try {
            repository.saveUser(user);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void deleteUser(User user) {
        DeleteUserEvent event = new DeleteUserEvent();
        try {
            repository.deleteUser(user);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }
}
