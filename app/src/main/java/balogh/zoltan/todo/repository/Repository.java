package balogh.zoltan.todo.repository;

import android.content.Context;

import java.util.List;

import balogh.zoltan.todo.model.Todo;
import balogh.zoltan.todo.model.User;

public interface Repository {

    void open(Context context);

    void close();

    User getUser();

    void saveUser(User user);

    void deleteUser(User user);

    Todo getTodo(long id);

    List<Todo> getTodos();

    void saveTodo(Todo todo);

    void deleteTodo(Todo todo);

    void deleteAll();
}