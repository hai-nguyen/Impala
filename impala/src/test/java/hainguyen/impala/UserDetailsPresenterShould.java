package hainguyen.impala;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hainguyen.impala.application.ApplicationBus;
import hainguyen.impala.model.api.LoginResponse;
import hainguyen.impala.feature.userdetails.presenter.UserDetailsPresenterImpl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by nguyenminhhai on 24/5/16.
 */
public class UserDetailsPresenterShould {

    ApplicationBus bus = mock(ApplicationBus.class);
    UserDetailsPresenterImpl userDetailsPresenter = new UserDetailsPresenterImpl(bus);
    LoginResponse response = LoginResponse.create(1, "TestName", "", "", "", "");

    @Before public void setUp() throws Exception {
        when(bus.getLoginResponse()).thenReturn(response);
    }

    @Test public void returnLoginResponseIfUserIsLoggedIn() {
        Assert.assertNotNull(userDetailsPresenter.getUserDetails());
        Assert.assertEquals(response.fullName(),
            userDetailsPresenter.getUserDetails().fullName());
    }
}
