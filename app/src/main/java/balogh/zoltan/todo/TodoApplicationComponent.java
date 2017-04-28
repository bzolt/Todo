package balogh.zoltan.todo;

import javax.inject.Singleton;

import balogh.zoltan.todo.interactor.InteractorModule;
import balogh.zoltan.todo.interactor.TodoInteractor;
import balogh.zoltan.todo.interactor.UserInteractor;
import balogh.zoltan.todo.repository.RepositoryModule;
import balogh.zoltan.todo.ui.UIModule;
import balogh.zoltan.todo.ui.login.LoginActivity;
import balogh.zoltan.todo.ui.tododetails.TodoDetailsActivity;
import balogh.zoltan.todo.ui.todoedit.TodoEditActivity;
import balogh.zoltan.todo.ui.todolist.TodoListActivity;
import dagger.Component;

@Singleton
@Component(modules = {UIModule.class, RepositoryModule.class, InteractorModule.class})
public interface TodoApplicationComponent {
    void inject(LoginActivity loginActivity);

    void inject(TodoListActivity todoListActivity);

    void inject(TodoDetailsActivity todoDetailsActivity);

    void inject(TodoEditActivity todoDetailsActivity);

    void inject(UserInteractor userInteractor);

    void inject(TodoInteractor todoInteractor);

    void inject(TodoApplication todoApplication);
}