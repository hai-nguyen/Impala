package hainguyen.impala.feature.userdetails.presenter;

import hainguyen.impala.model.api.LoginResponse;
import hainguyen.impala.feature.base.ImpalaPresenter;
import hainguyen.impala.feature.userdetails.view.UserDetailsView;

public interface UserDetailsPresenter extends ImpalaPresenter<UserDetailsView> {
    LoginResponse getUserDetails();
}
