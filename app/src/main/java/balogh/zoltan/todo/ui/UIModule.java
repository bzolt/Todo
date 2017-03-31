package balogh.zoltan.todo.ui;

import android.content.Context;


import javax.inject.Singleton;

import balogh.zoltan.todo.ui.login.LoginPresenter;
import balogh.zoltan.todo.ui.tododetails.TodoDetailsPresenter;
import balogh.zoltan.todo.ui.todoedit.TodoEditPresenter;
import balogh.zoltan.todo.ui.todolist.TodoListPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class UIModule {
    private Context context;

    public UIModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public LoginPresenter provideLoginPresenter() {
        return new LoginPresenter();
    }

    @Provides
    @Singleton
    public TodoListPresenter provideTodoListPresenter() {
        return new TodoListPresenter();
    }

    @Provides
    @Singleton
    public TodoDetailsPresenter provideTodoDetailsPresenter() {
        return new TodoDetailsPresenter();
    }

    @Provides
    @Singleton
    public TodoEditPresenter provideTodoEditPresenter() {
        return new TodoEditPresenter();
    }
}