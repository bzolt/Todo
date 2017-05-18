package balogh.zoltan.todo.repository;

import android.content.Context;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.List;

import balogh.zoltan.todo.model.Todo;
import balogh.zoltan.todo.model.User;

public class SugarOrmRepository implements Repository {
    @Override
    public void open(Context context) {
        SugarContext.init(context);
    }

    @Override
    public void close() {
        SugarContext.terminate();
    }

    @Override
    public User getUser() {
        return SugarRecord.findAll(User.class).next();
    }

    @Override
    public void saveUser(User user) {
        SugarRecord.save(user);
    }

    @Override
    public void deleteUser(User user) {
        SugarRecord.delete(user);
    }

    @Override
    public Todo getTodo(long id) {
        return SugarRecord.findById(Todo.class, id);
    }

    @Override
    public List<Todo> getTodos() {
        return SugarRecord.listAll(Todo.class);
    }

    @Override
    public void saveTodo(Todo todo) {
        SugarRecord.save(todo);
    }

    @Override
    public void deleteTodo(Todo todo) {
        SugarRecord.delete(todo);
    }

    @Override
    public void deleteAll() {
        SugarRecord.deleteAll(Todo.class);
        SugarRecord.deleteAll(User.class);
    }
}
