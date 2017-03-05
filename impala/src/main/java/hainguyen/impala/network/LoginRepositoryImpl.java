package hainguyen.impala.network;

import hainguyen.impala.network.model.LoginRequest;
import hainguyen.impala.network.model.LoginResponse;
import rx.Observable;

public class LoginRepositoryImpl implements LoginRepository {

    private LoginService loginService;

    public LoginRepositoryImpl(LoginService service) {
        this.loginService = service;
    }

    @Override public Observable<LoginResponse> login(LoginRequest request) {
        return loginService.login(request);
    }
}
