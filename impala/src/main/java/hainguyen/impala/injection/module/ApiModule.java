package hainguyen.impala.injection.module;

import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hainguyen.impala.application.Constants;
import hainguyen.impala.network.LoginRepository;
import hainguyen.impala.network.LoginRepositoryImpl;
import hainguyen.impala.network.LoginService;
import hainguyen.impala.util.ImpalaGsonTypeAdapter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
@Module
public class ApiModule {

    @Provides
    @Singleton
    @Named("LOGIN_URL")
    String provideLoginApiUrl() {
        return Constants.LOGIN_URL;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client,
                             @Named("LOGIN_URL") String url) {
        return new Retrofit.Builder().baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().registerTypeAdapterFactory(ImpalaGsonTypeAdapter.create())
                                .create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    LoginService provideLoginService(Retrofit retrofit) {
        return retrofit.create(LoginService.class);
    }

    @Provides
    @Singleton
    LoginRepository provideLoginRepository(LoginService service) {
        return new LoginRepositoryImpl(service);
    }
}
