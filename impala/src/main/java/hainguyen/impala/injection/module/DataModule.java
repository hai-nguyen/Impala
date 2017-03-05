package hainguyen.impala.injection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hainguyen.impala.data.UserProfile;
import hainguyen.impala.network.LoginRepository;

@Module
@Singleton
public class DataModule {
    @Provides
    @Singleton
    UserProfile provideUserProfile(LoginRepository loginRepository) {
        return new UserProfile(loginRepository);
    }
}
