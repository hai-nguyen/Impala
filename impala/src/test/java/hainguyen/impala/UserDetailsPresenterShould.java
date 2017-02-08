package hainguyen.impala;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import hainguyen.impala.application.ApplicationBus;
import hainguyen.impala.feature.userdetails.presenter.UserDetailsPresenterImpl;
import hainguyen.impala.feature.userdetails.view.UserDetailsView;
import hainguyen.impala.model.api.LoginResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class UserDetailsPresenterShould {

    ApplicationBus bus = mock(ApplicationBus.class);
    UserDetailsPresenterImpl userDetailsPresenter = new UserDetailsPresenterImpl(bus);
    LoginResponse response = LoginResponse.create(1, "TestName", "", "", "", "");
    UserDetailsView view = mock(UserDetailsView.class);

    @Before
    public void setUp() throws Exception {
        when(bus.getLoginResponse()).thenReturn(response);
        userDetailsPresenter.setView(view);
    }

    @Test
    public void returnLoginResponseIfUserIsLoggedIn() {
        userDetailsPresenter.getUserDetails();
        Mockito.verify(view).populateUserDetails(response);
    }
}
