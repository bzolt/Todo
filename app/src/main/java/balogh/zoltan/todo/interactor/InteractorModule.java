package balogh.zoltan.todo.interactor;

import dagger.Module;
import dagger.Provides;

@Module
public class InteractorModule {
    @Provides
    public UserInteractor provideUser() {
        return new UserInteractor();
    }

    @Provides
    public TodoInteractor provideTodo() {
        return new TodoInteractor();
    }
}