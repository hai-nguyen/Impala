package hainguyen.impala.injection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hainguyen.impala.network.LoginRepository;
import hainguyen.impala.application.ApplicationBus;
import hainguyen.impala.feature.login.presenter.LoginPresenter;
import hainguyen.impala.feature.login.presenter.LoginPresenterImpl;
import hainguyen.impala.feature.userdetails.presenter.UserDetailsPresenter;
import hainguyen.impala.feature.userdetails.presenter.UserDetailsPresenterImpl;
import hainguyen.impala.util.ContactUtil;

@Singleton
@Module public class PresenterModule {

    @Provides @Singleton
    LoginPresenter provideLoginPresenter(LoginRepository service,
                                         ContactUtil util, ApplicationBus bus) {
        return new LoginPresenterImpl(service, util, bus);
    }

    @Provides @Singleton UserDetailsPresenter provideUserDetailsPresenter(ApplicationBus bus) {
        return new UserDetailsPresenterImpl(bus);
    }
}
