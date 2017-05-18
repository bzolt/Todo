package balogh.zoltan.todo;

import javax.inject.Singleton;

import balogh.zoltan.todo.interactor.InteractorModule;
import balogh.zoltan.todo.mock.MockNetworkModule;
import balogh.zoltan.todo.repository.TestRepositoryModule;
import dagger.Component;

@Singleton
@Component(modules = {MockNetworkModule.class, TestModule.class, InteractorModule.class, TestRepositoryModule.class})
public interface TestComponent extends TodoApplicationComponent {
}