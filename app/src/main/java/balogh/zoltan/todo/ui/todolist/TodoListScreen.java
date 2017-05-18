package balogh.zoltan.todo.ui.todolist;

import java.util.List;

import balogh.zoltan.todo.model.Todo;

public interface TodoListScreen {
    void showTodoList(List<Todo> todos);

    void showSucces();

    void showDelete(Todo todo);

    void showError(int code);

    void logoutSuccess();
}