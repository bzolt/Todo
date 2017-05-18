package balogh.zoltan.todo.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import balogh.zoltan.todo.model.Todo;
import balogh.zoltan.todo.model.User;

public class MemoryRepository implements Repository {
    private User user;
    private Map<Long, Todo> todos = new HashMap<Long, Todo>();

    @Override
    public void open(Context context) {
        user = new User(1L, "user", "password");

        Todo todo1 = new Todo(2L, "Todo1", "Todo todo todo", false);
        Todo todo2 = new Todo(3L, "Todo2", "Todo todo todo", false);

        todos.put(todo1.getId(), todo1);
        todos.put(todo2.getId(), todo2);
    }

    @Override
    public void close() {

    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void saveUser(User user) {
        this.user = user;
    }

    @Override
    public void deleteUser(User user) {
        this.user = null;
    }

    @Override
    public Todo getTodo(long id) {
        return todos.get(id);
    }

    @Override
    public List<Todo> getTodos() {
        return new ArrayList<Todo>(todos.values());
    }

    @Override
    public void saveTodo(Todo todo) {
        if (todo.getId() == null) {
            Random r = new Random();
            long newId;
            do {
                newId = r.nextLong();
            } while (todos.containsKey(newId));
            todo.setId(newId);
        }
        todos.put(todo.getId(), todo);
    }

    @Override
    public void deleteTodo(Todo todo) {
        todos.remove(todo.getId());
    }

    @Override
    public void deleteAll() {
        user = null;
        todos.clear();
    }
}
