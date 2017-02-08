package hainguyen.impala.feature.userdetails.view;

import hainguyen.impala.feature.base.ImpalaView;
import hainguyen.impala.model.api.LoginResponse;

public interface UserDetailsView extends ImpalaView {
    void populateUserDetails(LoginResponse userDetails);
    void backToLoginScreen();
}