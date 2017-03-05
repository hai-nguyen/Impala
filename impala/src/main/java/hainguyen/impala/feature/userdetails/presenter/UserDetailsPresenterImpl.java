package hainguyen.impala.feature.userdetails.presenter;

import javax.inject.Inject;

import hainguyen.impala.application.ApplicationBus;
import hainguyen.impala.application.ImpalaApplication;
import hainguyen.impala.feature.userdetails.view.UserDetailsView;
import hainguyen.impala.model.User;

public class UserDetailsPresenterImpl implements UserDetailsPresenter {

    UserDetailsView detailsView;
    ApplicationBus bus;
    User user;

    @Inject
    public UserDetailsPresenterImpl(ApplicationBus applicationBus, User user) {
        this.user = user;
        this.bus = applicationBus;
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
