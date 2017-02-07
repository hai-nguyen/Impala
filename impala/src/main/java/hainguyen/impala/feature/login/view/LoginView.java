package hainguyen.impala.feature.login.view;

import java.util.List;

import hainguyen.impala.appsenum.Enums;
import hainguyen.impala.feature.base.ImpalaView;

public interface LoginView extends ImpalaView {
    void showProgress(final boolean show);

    boolean mayRequestContacts();

    void setEmailViewError(Enums.EmailErrorType errorType);

    void clearError();

    void setPasswordError();

    void addEmailsToAutoComplete(List<String> emailAddressCollection);

    void goToDetailsPage();
}
