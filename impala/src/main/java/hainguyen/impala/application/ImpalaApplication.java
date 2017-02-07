package hainguyen.impala.application;

import android.app.Application;

import hainguyen.impala.injection.ApiModule;
import hainguyen.impala.injection.AppComponent;
import hainguyen.impala.injection.AppModule;
import hainguyen.impala.injection.DaggerAppComponent;
import hainguyen.impala.injection.PresenterModule;

public class ImpalaApplication extends Application {

    private static ImpalaApplication instance;
    private AppComponent appComponent;

    public ImpalaApplication() {
        instance = this;
    }

    public static ImpalaApplication getInstance() {
        return instance;
    }

    public void onCreate() {
        super.onCreate();
        initializeApp();
    }

    private void initializeApp() {
        this.appComponent = DaggerAppComponent.builder()
            .appModule(new AppModule(this))
            .apiModule(new ApiModule())
            .presenterModule(new PresenterModule())
            .build();
    }

    /**
     * Get AppComponent
     */
    public AppComponent getAppComponent() {
        return appComponent;
    }
}
