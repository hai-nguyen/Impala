package hainguyen.impala.network;

import hainguyen.impala.network.model.LoginRequest;
import hainguyen.impala.network.model.LoginResponse;
import rx.Observable;

public interface LoginRepository {
    Observable<LoginResponse> login(LoginRequest request);
}
