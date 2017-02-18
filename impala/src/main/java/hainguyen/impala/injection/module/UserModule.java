package hainguyen.impala.injection.module;

import dagger.Module;
import dagger.Provides;
import hainguyen.impala.application.ApplicationBus;
import hainguyen.impala.feature.userdetails.presenter.UserDetailsPresenter;
import hainguyen.impala.feature.userdetails.presenter.UserDetailsPresenterImpl;
import hainguyen.impala.injection.scope.UserScope;
import hainguyen.impala.model.api.LoginResponse;

@Module
public class UserModule {

    private LoginResponse user;

    public UserModule(LoginResponse user) {
        this.user = user;
    }

    @Provides
    @UserScope
    LoginResponse provideUser() {
        return user;
    }

    @Provides
    @UserScope
    UserDetailsPresenter provideUserDetailsPresenter(ApplicationBus bus, LoginResponse user) {
        return new UserDetailsPresenterImpl(bus, user);
    }
}
