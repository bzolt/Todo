package balogh.zoltan.todo.ui.todoedit;

import balogh.zoltan.todo.model.Todo;

public interface TodoEditScreen {
    void fillTodo(Todo todo);

    void showSuccess();

    void showError(int code);

    void logoutSuccess();
}