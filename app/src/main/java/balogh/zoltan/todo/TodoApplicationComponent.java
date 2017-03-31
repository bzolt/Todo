package balogh.zoltan.todo;

import javax.inject.Singleton;

import balogh.zoltan.todo.ui.UIModule;
import balogh.zoltan.todo.ui.main.MainActivity;
import dagger.Component;

@Singleton
@Component(modules = {UIModule.class})
public interface TodoApplicationComponent {
    void inject(MainActivity mainActivity);

}