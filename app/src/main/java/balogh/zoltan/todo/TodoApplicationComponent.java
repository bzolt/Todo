package balogh.zoltan.todo;

import javax.inject.Singleton;

import balogh.zoltan.todo.interactor.InteractorModule;
import balogh.zoltan.todo.interactor.todo.TodoInteractor;
import balogh.zoltan.todo.interactor.user.UserInteractor;
import balogh.zoltan.todo.repository.RepositoryModule;
import balogh.zoltan.todo.ui.UIModule;
import balogh.zoltan.todo.ui.login.LoginActivity;
import balogh.zoltan.todo.ui.login.LoginPresenter;
import balogh.zoltan.todo.ui.tododetails.TodoDetailsActivity;
import balogh.zoltan.todo.ui.tododetails.TodoDetailsPresenter;
import balogh.zoltan.todo.ui.todoedit.TodoEditActivity;
import balogh.zoltan.todo.ui.todoedit.TodoEditPresenter;
import balogh.zoltan.todo.ui.todolist.TodoListActivity;
import balogh.zoltan.todo.ui.todolist.TodoListPresenter;
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

    void inject(LoginPresenter loginPresenter);

    void inject(TodoDetailsPresenter todoDetailsPresenter);

    void inject(TodoEditPresenter todoEditPresenter);

    void inject(TodoListPresenter todoListPresenter);

    void inject(TodoApplication todoApplication);
}