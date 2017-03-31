package balogh.zoltan.todo.ui.tododetails;

import balogh.zoltan.todo.ui.Presenter;

public class TodoDetailsPresenter extends Presenter<TodoDetailsScreen> {

    public TodoDetailsPresenter() {
    }

    @Override
    public void attachScreen(TodoDetailsScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void loadTodo(int id)
    {
        // TODO
        screen.showTodo("TODO");
    }

    public void checkTodo(int id)
    {
        // TODO
    }

    public void uncheckTodo(int id)
    {
        // TODO
    }

    public void deleteTodo(int id)
    {
        // TODO
    }
}