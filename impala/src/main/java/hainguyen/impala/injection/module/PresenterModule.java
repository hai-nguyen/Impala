package hainguyen.impala.injection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hainguyen.impala.application.ApplicationBus;
import hainguyen.impala.feature.login.presenter.LoginPresenter;
import hainguyen.impala.feature.login.presenter.LoginPresenterImpl;
import hainguyen.impala.network.LoginRepository;
import hainguyen.impala.util.ContactUtil;

@Singleton
@Module public class PresenterModule {

    @Provides @Singleton
    LoginPresenter provideLoginPresenter(LoginRepository service,
                                         ContactUtil util, ApplicationBus bus) {
        return new LoginPresenterImpl(service, util, bus);
    }
}
