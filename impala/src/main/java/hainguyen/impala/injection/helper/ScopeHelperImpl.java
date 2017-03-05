package hainguyen.impala.injection.helper;

import hainguyen.impala.application.ImpalaApplication;
import hainguyen.impala.model.api.LoginResponse;

public class ScopeHelperImpl implements ScopeHelper {

    @Override
    public void initUserScope(LoginResponse response) {
        ImpalaApplication.getInstance().createUserComponent(response);

    }
}
