package hainguyen.impala.feature.userdetails.view;

import hainguyen.impala.feature.base.ImpalaView;
import hainguyen.impala.model.User;

public interface UserDetailsView extends ImpalaView {
    void populateUserDetails(User userDetails);
    void backToLoginScreen();
}