package balogh.zoltan.todo;

import android.content.Context;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import balogh.zoltan.todo.ui.UIModule;
import balogh.zoltan.todo.ui.login.LoginPresenter;
import balogh.zoltan.todo.ui.tododetails.TodoDetailsPresenter;
import balogh.zoltan.todo.ui.todoedit.TodoEditPresenter;
import balogh.zoltan.todo.ui.todolist.TodoListPresenter;
import balogh.zoltan.todo.utils.UiExecutor;
import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;

@Module
public class TestModule {

    private final UIModule UIModule;

    public TestModule(Context context) {
        this.UIModule = new UIModule(context);
    }

    @Provides
    public Context provideContext() {
        return UIModule.provideContext();
    }


    @Provides
    public LoginPresenter provideLoginPresenter() {
        return UIModule.provideLoginPresenter();
    }

    @Provides
    public TodoListPresenter provideTodoListPresenter() {
        return UIModule.provideTodoListPresenter();
    }

    @Provides
    public TodoDetailsPresenter provideTodoDetailsPresenter() {
        return UIModule.provideTodoDetailsPresenter();
    }

    @Provides
    public TodoEditPresenter provideTodoEditPresenter() {
        return UIModule.provideTodoEditPresenter();
    }

    @Provides
    @Singleton
    public EventBus provideEventBus() {
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    public Executor provideNetworkExecutor() {
        return new UiExecutor();
    }


}