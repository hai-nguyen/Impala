package hainguyen.impala.network;

import hainguyen.impala.model.api.LoginRequest;
import hainguyen.impala.model.api.LoginResponse;
import rx.Observable;

public class LoginRepositoryImpl implements LoginRepository {

    private LoginService loginService;

    public LoginRepositoryImpl(LoginService service) {
        this.loginService = service;
    }

    @Override public Observable<LoginResponse> login(String email, String password) {
        LoginRequest request = LoginRequest.builder().setEmail(email).setPassword(password).build();
        return loginService.login(request);
    }
}
