package hainguyen.impala.injection.component;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import hainguyen.impala.feature.login.view.LoginActivity;
import hainguyen.impala.injection.module.ApiModule;
import hainguyen.impala.injection.module.AppModule;
import hainguyen.impala.injection.module.PresenterModule;
import hainguyen.impala.injection.module.UserModule;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class, PresenterModule.class})
public interface AppComponent {

    UserComponent plus(UserModule userModule);

    Gson gson();

    void inject(LoginActivity loginActivity);
}
