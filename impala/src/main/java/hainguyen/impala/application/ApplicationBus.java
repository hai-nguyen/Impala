package hainguyen.impala.application;

import hainguyen.impala.model.api.LoginResponse;

public class ApplicationBus {

    private LoginResponse loginResponse;

    public LoginResponse getLoginResponse() {
        return loginResponse;
    }

    public void setLoginResponse(LoginResponse loginResponse) {
        this.loginResponse = loginResponse;
    }
}
