package hainguyen.impala.feature.userdetails.presenter;

import javax.inject.Inject;

import hainguyen.impala.application.ApplicationBus;
import hainguyen.impala.application.ImpalaApplication;
import hainguyen.impala.feature.userdetails.view.UserDetailsView;
import hainguyen.impala.model.api.LoginResponse;

public class UserDetailsPresenterImpl implements UserDetailsPresenter {

    UserDetailsView detailsView;
    ApplicationBus bus;
    LoginResponse user;

    @Inject
    public UserDetailsPresenterImpl(ApplicationBus applicationBus, LoginResponse loginResponse) {
        user = loginResponse;
        bus = applicationBus;
    }

    @Override
    public void setView(UserDetailsView view) {
        detailsView = view;
    }

    @Override
    public void destroyView() {
        detailsView = null;
    }

    @Override
    public void getUserDetails() {
        if (bus.isLogin()) {
            detailsView.populateUserDetails(user);
        } else {
            detailsView.backToLoginScreen();
        }
    }

    @Override
    public void releaseUserScope() {
        ImpalaApplication.getInstance().releaseUserComponent();
        bus.setLogin(false);
    }
}
