package hainguyen.impala.injection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hainguyen.impala.application.ApplicationBus;
import hainguyen.impala.data.UserProfile;
import hainguyen.impala.feature.login.presenter.LoginPresenter;
import hainguyen.impala.feature.login.presenter.LoginPresenterImpl;
import hainguyen.impala.injection.helper.ScopeHelper;
import hainguyen.impala.util.ContactUtil;

@Singleton
@Module
public class PresenterModule {

    @Provides
    @Singleton
    LoginPresenter provideLoginPresenter(UserProfile userProfile,
                                         ContactUtil util, ApplicationBus bus, ScopeHelper scopeHelper) {
        return new LoginPresenterImpl(userProfile, util, bus, scopeHelper);
    }
}
