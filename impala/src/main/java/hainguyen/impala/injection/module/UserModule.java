package hainguyen.impala.injection.module;

import dagger.Module;
import dagger.Provides;
import hainguyen.impala.application.ApplicationBus;
import hainguyen.impala.feature.userdetails.presenter.UserDetailsPresenter;
import hainguyen.impala.feature.userdetails.presenter.UserDetailsPresenterImpl;
import hainguyen.impala.injection.scope.UserScope;
import hainguyen.impala.model.User;

@Module
public class UserModule {

    private User user;

    public UserModule(User user) {
        this.user = user;
    }

    @Provides
    @UserScope
    User provideUser() {
        return user;
    }

    @Provides
    @UserScope
    UserDetailsPresenter provideUserDetailsPresenter(ApplicationBus bus, User user) {
        return new UserDetailsPresenterImpl(bus, user);
    }
}
