package hainguyen.impala.data;


import javax.inject.Inject;

import hainguyen.impala.model.User;
import hainguyen.impala.network.LoginRepository;
import hainguyen.impala.network.model.LoginRequest;
import hainguyen.impala.network.model.LoginResponse;
import rx.Observable;
import rx.functions.Func1;

public class UserProfile {

    private LoginRepository loginRepository;

    @Inject
    public UserProfile(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public Observable<User> login(String email, String password) {
        LoginRequest request = LoginRequest.builder().setEmail(email).setPassword(password).build();
        return loginRepository.login(request).flatMap(new Func1<LoginResponse, Observable<User>>() {
            @Override
            public Observable<User> call(LoginResponse response) {
                User user = new User();
                user.transformFrom(response);
                return Observable.just(user);
            }
        });
    }
}
