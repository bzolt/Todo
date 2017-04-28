package balogh.zoltan.todo.ui.tododetails;

import balogh.zoltan.todo.model.Todo;

public interface TodoDetailsScreen {
    void showTodo(Todo todo);

    void showSucces();

    void showError(String text);
}