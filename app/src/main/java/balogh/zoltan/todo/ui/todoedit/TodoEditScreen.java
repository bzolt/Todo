package balogh.zoltan.todo.ui.todoedit;

public interface TodoEditScreen {
    void fillTodo(String todo);
    void editSuccess();
    void editError(String text);
}