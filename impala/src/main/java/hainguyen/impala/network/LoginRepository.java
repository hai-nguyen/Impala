package hainguyen.impala.network;

import hainguyen.impala.model.api.LoginResponse;
import rx.Observable;

public interface LoginRepository {
    Observable<LoginResponse> login(String email, String password);
}
