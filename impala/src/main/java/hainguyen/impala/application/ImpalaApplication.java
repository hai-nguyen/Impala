package hainguyen.impala.application;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import hainguyen.impala.injection.component.AppComponent;
import hainguyen.impala.injection.component.DaggerAppComponent;
import hainguyen.impala.injection.component.UserComponent;
import hainguyen.impala.injection.module.ApiModule;
import hainguyen.impala.injection.module.AppModule;
import hainguyen.impala.injection.module.DataModule;
import hainguyen.impala.injection.module.PresenterModule;
import hainguyen.impala.injection.module.UserModule;
import hainguyen.impala.model.User;

public class ImpalaApplication extends Application {

    private static ImpalaApplication instance;
    private AppComponent appComponent;
    private UserComponent userComponent;

    public ImpalaApplication() {
        instance = this;
    }

    public static ImpalaApplication getInstance() {
        return instance;
    }

    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        initializeApp();
    }

    private void initializeApp() {
        this.appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule())
                .dataModule(new DataModule())
                .presenterModule(new PresenterModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public UserComponent getUserComponent() {
        return userComponent;
    }

    public UserComponent createUserComponent(User user) {
        userComponent = appComponent.plus(new UserModule(user));
        return userComponent;
    }

    public void releaseUserComponent() {
        this.userComponent = null;
    }

}
