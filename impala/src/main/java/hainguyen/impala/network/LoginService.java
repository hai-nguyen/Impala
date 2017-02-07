package hainguyen.impala.network;

import hainguyen.impala.model.api.LoginRequest;
import hainguyen.impala.model.api.LoginResponse;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface LoginService {
    @POST("impala/login") Observable<LoginResponse> login(@Body LoginRequest request);
}
