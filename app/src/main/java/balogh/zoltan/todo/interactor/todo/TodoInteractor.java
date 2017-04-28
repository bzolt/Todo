package balogh.zoltan.todo.interactor.todo;

import javax.inject.Inject;

import balogh.zoltan.todo.model.Todo;
import balogh.zoltan.todo.repository.Repository;

public class TodoInteractor {
    @Inject
    Repository repository;

    void getTodo(long id) {
        repository.getTodo(id);
    }

    void getTodos() {
        repository.getTodos();
    }

    void saveTodo(Todo todo) {
        repository.saveTodo(todo);
    }

    void deleteTodo(Todo todo) {
        repository.deleteTodo(todo);
    }

    void isInDB(Todo todo) {
        repository.isInDB(todo);
    }
}
