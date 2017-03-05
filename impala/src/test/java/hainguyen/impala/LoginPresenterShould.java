package hainguyen.impala;

import android.content.ContentResolver;
import android.test.mock.MockContentResolver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import hainguyen.impala.application.ApplicationBus;
import hainguyen.impala.appsenum.Enums;
import hainguyen.impala.data.UserProfile;
import hainguyen.impala.feature.login.presenter.LoginPresenterImpl;
import hainguyen.impala.feature.login.view.LoginView;
import hainguyen.impala.injection.helper.ScopeHelper;
import hainguyen.impala.model.User;
import hainguyen.impala.util.ContactUtil;
import hainguyen.impala.utils.SynchronousSchedulers;
import rx.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class LoginPresenterShould {

    private LoginPresenterImpl loginPresenter;
    private LoginView view = mock(LoginView.class);
    private UserProfile userProfile = mock(UserProfile.class);
    private User response = mock(User.class);
    private Throwable errorResponse = new Throwable("NetworkError");
    private ContactUtil contactUtil = mock(ContactUtil.class);
    private ApplicationBus bus = mock(ApplicationBus.class);
    private ScopeHelper scopeHelper = mock(ScopeHelper.class);

    @Rule
    public SynchronousSchedulers schedulers = new SynchronousSchedulers();

    @Before
    public void setUp() {
        loginPresenter = new LoginPresenterImpl(userProfile, contactUtil, bus, scopeHelper);
        loginPresenter.setView(view);
    }

    @Test
    public void showEmailInvalidIfEmailIsNotWellFormatted() {
        loginPresenter.validateEmail("12121212");

        verify(view).setEmailViewError(Enums.EmailErrorType.INVALID);
    }

    @Test
    public void showEmailInvalidIfEmailIsEmpty() {
        loginPresenter.validateEmail("");

        verify(view).setEmailViewError(Enums.EmailErrorType.EMPTY);
    }

    @Test
    public void returnTrueIfTheEmailIsCorrect() {
        Assert.assertTrue(loginPresenter.validateEmail("test@email.com"));
    }

    @Test
    public void showPasswordInvalidMessageIfPasswordIsInvalid() {
        loginPresenter.validatePassword("123");

        verify(view).setPasswordError();
    }

    @Test
    public void returnTrueIfPasswordIsValid() {
        Assert.assertTrue(loginPresenter.validatePassword("p@ssW0rd"));
    }

    @Test
    public void logUserInIfTheCredentialsAreCorrect() {
        when(userProfile.login("test@cba.com", "12345")).thenReturn(Observable.just(response));
        loginPresenter.attemptLogin("test@cba.com", "12345");
        when(bus.isLogin()).thenReturn(true);
        verify(view).goToDetailsPage();
    }

    @Test
    public void populateContactToEmailList() {
        List<String> contactList = new ArrayList<>(2);
        ContentResolver resolver = new MockContentResolver();
        when(contactUtil.getEmailList(resolver)).thenReturn(Observable.just(contactList));

        loginPresenter.populateAutoComplete(resolver);

        verify(view).addEmailsToAutoComplete(contactList);
    }

    @Test
    public void showErrorIfNetworkErrorOccur() {
        when(userProfile.login("test@cba.com", "12345")).thenReturn(
                Observable.<User>error(errorResponse));
        loginPresenter.attemptLogin("test@cba.com", "12345");

        verify(view).showProgress(false);
    }
}
