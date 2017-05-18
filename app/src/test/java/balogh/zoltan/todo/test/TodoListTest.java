package balogh.zoltan.todo.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.robolectric.annotation.Config;

import java.util.List;

import balogh.zoltan.todo.BuildConfig;
import balogh.zoltan.todo.model.Todo;
import balogh.zoltan.todo.ui.todolist.TodoListPresenter;
import balogh.zoltan.todo.ui.todolist.TodoListScreen;
import balogh.zoltan.todo.utils.RobolectricDaggerTestRunner;

import static balogh.zoltan.todo.TestHelper.setTestInjector;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class TodoListTest {

    @Captor
    ArgumentCaptor<List<Todo>> captor;
    private TodoListPresenter todoListPresenter;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        setTestInjector();
        todoListPresenter = new TodoListPresenter();
    }

    @Test
    public void testLoadTodoList() {
        TodoListScreen todoListScreen = mock(TodoListScreen.class);
        todoListPresenter.attachScreen(todoListScreen);
        todoListPresenter.loadTodoList();

        verify(todoListScreen, times(1)).showTodoList(captor.capture());
        assertEquals(2, captor.getValue().size());
        assertEquals("Todo1", captor.getValue().get(0).getTitle());
    }

    @Test
    public void testCheckTodo() {
        TodoListScreen todoListScreen = mock(TodoListScreen.class);
        todoListPresenter.attachScreen(todoListScreen);
        Todo todo = new Todo();
        todo.setTitle("Todo");
        todo.setContent("Content");
        todoListPresenter.checkTodo(todo, true);

        verify(todoListScreen, times(1)).showSucces();
    }

    @Test
    public void testDeleteTodo() {
        TodoListScreen todoListScreen = mock(TodoListScreen.class);
        todoListPresenter.attachScreen(todoListScreen);
        Todo todo = new Todo();
        todo.setId(2L);
        todoListPresenter.deleteTodo(todo);

        verify(todoListScreen, times(1)).showDelete(todo);
    }

    @Test
    public void testLogout() {
        TodoListScreen todoListScreen = mock(TodoListScreen.class);
        todoListPresenter.attachScreen(todoListScreen);
        todoListPresenter.logout();

        verify(todoListScreen, times(1)).logoutSuccess();
    }

    @After
    public void tearDown() {
        todoListPresenter.detachScreen();
    }
}
