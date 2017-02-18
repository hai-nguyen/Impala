package hainguyen.impala.injection.component;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import hainguyen.impala.feature.login.presenter.LoginPresenterImpl;
import hainguyen.impala.feature.login.view.LoginActivity;
import hainguyen.impala.feature.userdetails.view.UserDetailsActivity;
import hainguyen.impala.injection.module.ApiModule;
import hainguyen.impala.injection.module.AppModule;
import hainguyen.impala.injection.module.PresenterModule;

@Singleton @Component(modules = { AppModule.class, ApiModule.class, PresenterModule.class })
public interface AppComponent {

    Gson gson();

    void inject(LoginActivity loginActivity);

    void inject(LoginPresenterImpl loginPresenter);

    void inject(UserDetailsActivity userDetailsActivity);
}
