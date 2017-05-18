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
import balogh.zoltan.todo.ui.tododetails.TodoDetailsPresenter;
import balogh.zoltan.todo.ui.tododetails.TodoDetailsScreen;
import balogh.zoltan.todo.utils.RobolectricDaggerTestRunner;

import static balogh.zoltan.todo.TestHelper.setTestInjector;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class TodoDetailsTest {

    @Captor
    ArgumentCaptor<Todo> captor;
    private TodoDetailsPresenter todoDetailsPresenter;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        setTestInjector();
        todoDetailsPresenter = new TodoDetailsPresenter();
    }

    @Test
    public void testLoadTodo() {
        TodoDetailsScreen todoDetailsScreen = mock(TodoDetailsScreen.class);
        todoDetailsPresenter.attachScreen(todoDetailsScreen);
        todoDetailsPresenter.loadTodo(2L);

        verify(todoDetailsScreen, times(1)).showTodo(captor.capture());
        assertEquals("Todo1", captor.getValue().getTitle());
        assertEquals("Todo todo todo", captor.getValue().getContent());
        assertEquals(false, captor.getValue().isDone());
    }

    @Test
    public void testLoadTodoNetwork() {
        TodoDetailsScreen todoDetailsScreen = mock(TodoDetailsScreen.class);
        todoDetailsPresenter.attachScreen(todoDetailsScreen);
        todoDetailsPresenter.loadTodo(4L);

        verify(todoDetailsScreen, times(1)).showTodo(captor.capture());
        assertEquals("Todo3", captor.getValue().getTitle());
        assertEquals("Todo todo todo", captor.getValue().getContent());
        assertEquals(true, captor.getValue().isDone());
    }

    @Test
    public void testLoadTodoNonExistent() {
        TodoDetailsScreen todoDetailsScreen = mock(TodoDetailsScreen.class);
        todoDetailsPresenter.attachScreen(todoDetailsScreen);
        todoDetailsPresenter.loadTodo(1L);

        verify(todoDetailsScreen, times(1)).showError(R.string.network_error);
    }

    @Test
    public void testCheckTodo() {
        TodoDetailsScreen todoDetailsScreen = mock(TodoDetailsScreen.class);
        todoDetailsPresenter.attachScreen(todoDetailsScreen);
        Todo todo = new Todo();
        todo.setTitle("Todo");
        todo.setContent("Content");
        todoDetailsPresenter.checkTodo(todo, true);

        verify(todoDetailsScreen, times(1)).showSucces();
    }

    @Test
    public void testLogout() {
        TodoDetailsScreen todoDetailsScreen = mock(TodoDetailsScreen.class);
        todoDetailsPresenter.attachScreen(todoDetailsScreen);
        todoDetailsPresenter.logout();

        verify(todoDetailsScreen, times(1)).logoutSuccess();
    }

    @After
    public void tearDown() {
        todoDetailsPresenter.detachScreen();
    }
}
