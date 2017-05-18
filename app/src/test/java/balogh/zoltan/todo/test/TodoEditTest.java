package balogh.zoltan.todo.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.robolectric.annotation.Config;

import balogh.zoltan.todo.BuildConfig;
import balogh.zoltan.todo.R;
import balogh.zoltan.todo.model.Todo;
import balogh.zoltan.todo.ui.todoedit.TodoEditPresenter;
import balogh.zoltan.todo.ui.todoedit.TodoEditScreen;
import balogh.zoltan.todo.utils.RobolectricDaggerTestRunner;

import static balogh.zoltan.todo.TestHelper.setTestInjector;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class TodoEditTest {

    @Captor
    ArgumentCaptor<Todo> captor;
    private TodoEditPresenter todoEditPresenter;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        setTestInjector();
        todoEditPresenter = new TodoEditPresenter();
    }

    @Test
    public void testLoadTodo() {
        TodoEditScreen todoEditScreen = mock(TodoEditScreen.class);
        todoEditPresenter.attachScreen(todoEditScreen);
        todoEditPresenter.loadTodo(2L);

        verify(todoEditScreen, times(1)).fillTodo(captor.capture());
        assertEquals("Todo1", captor.getValue().getTitle());
        assertEquals("Todo todo todo", captor.getValue().getContent());
        assertEquals(false, captor.getValue().isDone());
    }

    @Test
    public void testLoadTodoNetwork() {
        TodoEditScreen todoEditScreen = mock(TodoEditScreen.class);
        todoEditPresenter.attachScreen(todoEditScreen);
        todoEditPresenter.loadTodo(4L);

        verify(todoEditScreen, times(1)).fillTodo(captor.capture());
        assertEquals("Todo3", captor.getValue().getTitle());
        assertEquals("Todo todo todo", captor.getValue().getContent());
        assertEquals(true, captor.getValue().isDone());
    }

    @Test
    public void testLoadTodoNonExistent() {
        TodoEditScreen todoEditScreen = mock(TodoEditScreen.class);
        todoEditPresenter.attachScreen(todoEditScreen);
        todoEditPresenter.loadTodo(1L);

        verify(todoEditScreen, times(1)).showError(R.string.network_error);
    }

    @Test
    public void testSaveTodo() {
        TodoEditScreen todoEditScreen = mock(TodoEditScreen.class);
        todoEditPresenter.attachScreen(todoEditScreen);
        Todo todo = new Todo();
        todo.setTitle("Todo");
        todo.setContent("Content");
        todo.setDone(false);
        todoEditPresenter.saveTodo(todo);

        verify(todoEditScreen, times(1)).showSuccess();
    }

    @Test
    public void testLogout() {
        TodoEditScreen todoEditScreen = mock(TodoEditScreen.class);
        todoEditPresenter.attachScreen(todoEditScreen);
        todoEditPresenter.logout();

        verify(todoEditScreen, times(1)).logoutSuccess();
    }

    @After
    public void tearDown() {
        todoEditPresenter.detachScreen();
    }
}
