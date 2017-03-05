package hainguyen.impala;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import hainguyen.impala.application.ApplicationBus;
import hainguyen.impala.feature.userdetails.presenter.UserDetailsPresenterImpl;
import hainguyen.impala.feature.userdetails.view.UserDetailsView;
import hainguyen.impala.model.User;
import hainguyen.impala.network.model.LoginResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class UserDetailsPresenterShould {

    ApplicationBus bus = mock(ApplicationBus.class);
    LoginResponse response = LoginResponse.create(1, "TestName", "", "", "", "");
    User user = new User().transformFrom(response);
    UserDetailsPresenterImpl userDetailsPresenter = new UserDetailsPresenterImpl(bus, user);
    UserDetailsView view = mock(UserDetailsView.class);

    @Before
    public void setUp() throws Exception {
        userDetailsPresenter.setView(view);
    }

    @Test
    public void returnLoginResponseIfUserIsLoggedIn() {
        when(bus.isLogin()).thenReturn(true);
        userDetailsPresenter.getUserDetails();
        Mockito.verify(view).populateUserDetails(user);
    }

    @Test
    public void goBackLoginScreenIfUserIsNotLoggedIn() {
        when(bus.isLogin()).thenReturn(false);
        userDetailsPresenter.getUserDetails();
        Mockito.verify(view).backToLoginScreen();
    }
}
