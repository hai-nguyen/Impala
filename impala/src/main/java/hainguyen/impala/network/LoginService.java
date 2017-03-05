package hainguyen.impala.network;

import hainguyen.impala.network.model.LoginRequest;
import hainguyen.impala.network.model.LoginResponse;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface LoginService {
    @POST("impala/login") Observable<LoginResponse> login(@Body LoginRequest request);
}
