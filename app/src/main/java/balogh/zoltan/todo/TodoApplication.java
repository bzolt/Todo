package balogh.zoltan.todo;

import android.app.Application;

import javax.inject.Inject;

import balogh.zoltan.todo.repository.Repository;
import balogh.zoltan.todo.ui.UIModule;

public class TodoApplication extends Application {

    public static TodoApplicationComponent injector;
    @Inject
    Repository repository;

    @Override
    public void onCreate() {
        super.onCreate();

        injector = DaggerTodoApplicationComponent.builder().uIModule(new UIModule(this)).build();

        injector.inject(this);
        repository.open(getApplicationContext());
    }
}