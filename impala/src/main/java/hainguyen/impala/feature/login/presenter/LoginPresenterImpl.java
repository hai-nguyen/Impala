package hainguyen.impala.feature.login.presenter;

import android.content.ContentResolver;

import java.util.List;

import javax.inject.Inject;

import hainguyen.impala.application.ApplicationBus;
import hainguyen.impala.application.scheduler.ImpalaScheduler;
import hainguyen.impala.appsenum.Enums;
import hainguyen.impala.feature.login.view.LoginView;
import hainguyen.impala.injection.helper.ScopeHelper;
import hainguyen.impala.model.api.LoginResponse;
import hainguyen.impala.network.LoginRepository;
import hainguyen.impala.util.ContactUtil;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView loginView;

    LoginRepository service;
    ContactUtil contactUtil;
    ApplicationBus bus;
    CompositeSubscription loginSubscriptions;
    ScopeHelper scopeHelper;

    @Inject
    public LoginPresenterImpl(LoginRepository service, ContactUtil util,
                              ApplicationBus applicationBus, ScopeHelper scopeHelper) {
        this.service = service;
        this.contactUtil = util;
        this.bus = applicationBus;
        this.scopeHelper = scopeHelper;
    }

    @Override
    public void setView(LoginView view) {
        loginView = view;
        loginSubscriptions = new CompositeSubscription();
    }

    @Override
    public void destroyView() {
        loginView = null;
        if (loginSubscriptions != null) {
            loginSubscriptions.unsubscribe();
            loginSubscriptions = null;
        }
    }

    @Override
    public void populateAutoComplete(final ContentResolver resolver) {
        loginView.showProgress(true);
        Subscription contactSubscription = contactUtil.getEmailList(resolver)
                .subscribeOn(ImpalaScheduler.io())
                .observeOn(ImpalaScheduler.mainThread())
                .subscribe(new Subscriber<List<String>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        populateAutoComplete(resolver);
                    }

                    @Override
                    public void onNext(List<String> emails) {
                        loginView.addEmailsToAutoComplete(emails);
                        loginView.showProgress(false);
                    }
                });
        loginSubscriptions.add(contactSubscription);
    }

    @Override
    public void attemptLogin(String email, String password) {
        loginView.clearError();
        loginView.showProgress(true);
        Subscription loginServiceSubscription = service.login(email, password)
                .subscribeOn(ImpalaScheduler.io())
                .observeOn(ImpalaScheduler.mainThread())
                .subscribe(new Subscriber<LoginResponse>() {
                    @Override
                    public void onCompleted() {
                        //Do nothing
                    }

                    @Override
                    public void onError(Throwable e) {
                        loginView.showProgress(false);
                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        scopeHelper.initUserScope(loginResponse);
                        bus.setLogin(true);
                        loginView.showProgress(false);
                        loginView.goToDetailsPage();
                    }
                });
        loginSubscriptions.add(loginServiceSubscription);
    }

    @Override
    public boolean validateEmail(String email) {
        if (email.length() <= 0) {
            loginView.setEmailViewError(Enums.EmailErrorType.EMPTY);
            return false;
        } else if (!isEmailValid(email)) {
            loginView.setEmailViewError(Enums.EmailErrorType.INVALID);
            return false;
        }
        return true;
    }

    @Override
    public boolean validatePassword(String password) {
        if (password.length() > 0 && !isPasswordValid(password)) {
            loginView.setPasswordError();
            return false;
        }
        return true;
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }
}
