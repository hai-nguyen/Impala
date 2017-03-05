package hainguyen.impala.injection.helper;

import hainguyen.impala.model.api.LoginResponse;

public interface ScopeHelper {
    public void initUserScope(LoginResponse response);
}
