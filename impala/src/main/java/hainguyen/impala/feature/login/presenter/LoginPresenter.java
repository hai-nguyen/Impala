package hainguyen.impala.feature.login.presenter;

import android.content.ContentResolver;
import hainguyen.impala.feature.base.ImpalaPresenter;
import hainguyen.impala.feature.login.view.LoginView;

public interface LoginPresenter extends ImpalaPresenter<LoginView> {
    void populateAutoComplete(ContentResolver resolver);

    void attemptLogin(String email, String password);

    boolean validateEmail(String email);

    boolean validatePassword(String password);
}
