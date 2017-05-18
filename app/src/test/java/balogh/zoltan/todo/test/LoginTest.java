package balogh.zoltan.todo.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import balogh.zoltan.todo.BuildConfig;
import balogh.zoltan.todo.ui.login.LoginPresenter;
import balogh.zoltan.todo.ui.login.LoginScreen;
import balogh.zoltan.todo.utils.RobolectricDaggerTestRunner;

import static balogh.zoltan.todo.TestHelper.setTestInjector;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class LoginTest {

    private LoginPresenter loginPresenter;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        loginPresenter = new LoginPresenter();
    }

    @Test
    public void testLogin() {
        LoginScreen loginScreen = mock(LoginScreen.class);
        loginPresenter.attachScreen(loginScreen);
        loginPresenter.login("asd", "asd");

        verify(loginScreen, times(1)).showSuccess();
    }

    @Test
    public void testCheckLogin() {
        LoginScreen loginScreen = mock(LoginScreen.class);
        loginPresenter.attachScreen(loginScreen);
        loginPresenter.checkLogin();

        verify(loginScreen, times(1)).showSuccess();
    }

    @After
    public void tearDown() {
        loginPresenter.detachScreen();
    }
}