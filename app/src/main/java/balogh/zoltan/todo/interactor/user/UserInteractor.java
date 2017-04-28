package balogh.zoltan.todo.interactor.user;

import javax.inject.Inject;

import balogh.zoltan.todo.model.User;
import balogh.zoltan.todo.repository.Repository;

public class UserInteractor {
    @Inject
    Repository repository;

    public void getUser() {
        repository.getUser();
    }

    public void saveUser(User user) {
        repository.saveUser(user);
    }

    public void deleteUser(User user) {
        repository.deleteUser(user);
    }
}
