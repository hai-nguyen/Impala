package hainguyen.impala.injection.helper;

import hainguyen.impala.application.ImpalaApplication;
import hainguyen.impala.model.User;

public class ScopeHelperImpl implements ScopeHelper {

    @Override
    public void initUserScope(User user) {
        ImpalaApplication.getInstance().createUserComponent(user);

    }
}
