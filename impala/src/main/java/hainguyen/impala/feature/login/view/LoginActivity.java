package hainguyen.impala.feature.login.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import hainguyen.impala.R;
import hainguyen.impala.application.ImpalaApplication;
import hainguyen.impala.appsenum.Enums;
import hainguyen.impala.feature.base.BaseActivity;
import hainguyen.impala.feature.login.presenter.LoginPresenter;
import hainguyen.impala.feature.userdetails.view.UserDetailsActivity;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements LoginView {

    @Inject
    LoginPresenter loginPresenter;

    private static final int REQUEST_READ_CONTACTS = 0;

    // UI references.
    @BindView(R.id.email)
    AutoCompleteTextView emailView;
    @BindView(R.id.password)
    EditText passwordView;
    @BindView(R.id.login_progress)
    View progressView;
    @BindView(R.id.login_form)
    View loginFormView;

    @Override
    public void setupViewComponent() {
        ImpalaApplication.getInstance().getAppComponent().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setupViewComponent();
        loginPresenter.setView(this);
        if (mayRequestContacts()) {
            loginPresenter.populateAutoComplete(getContentResolver());
        }
    }

    @OnEditorAction(R.id.password)
    boolean onEditorAction(TextView textView, int id,
                           KeyEvent keyEvent) {
        if (id == R.id.login || id == EditorInfo.IME_NULL) {
            login();
            return true;
        }
        return false;
    }

    @OnClick(R.id.email_sign_in_button)
    void onSignInButtonClicked(View view) {
        login();
    }

    private void login() {
        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();
        if (loginPresenter.validateEmail(email) && loginPresenter.validatePassword(password)) {
            loginPresenter.attemptLogin(email, password);
        }
    }

    @Override
    public boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(emailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    @Override
    public void setEmailViewError(Enums.EmailErrorType errorType) {
        switch (errorType) {
            case EMPTY:
                showEmailRequireError();
                break;
            case INVALID:
                showEmailInvalidError();
                break;
            default:
                clearError();
        }
    }

    private void showEmailError(String string) {
        emailView.setError(string);
        emailView.requestFocus();
    }

    private void showEmailRequireError() {
        showEmailError(getString(R.string.error_field_required));
    }

    private void showEmailInvalidError() {
        showEmailError(getString(R.string.error_invalid_email));
    }

    @Override
    public void clearError() {
        emailView.setError(null);
        passwordView.setError(null);
    }

    @Override
    public void setPasswordError() {
        passwordView.setError(getString(R.string.error_invalid_password));
        passwordView.requestFocus();
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS && isPermissionGranted(grantResults)) {
            loginPresenter.populateAutoComplete(getContentResolver());
        }
    }

    private boolean isPermissionGranted(@NonNull int[] grantResults) {
        return grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this, android.R.layout.simple_dropdown_item_1line,
                        emailAddressCollection);

        emailView.setAdapter(adapter);
    }

    @Override
    public void goToDetailsPage() {
        Intent intent = new Intent(this, UserDetailsActivity.class);
        startActivity(intent);
        this.finish();
    }

    /**
     * Shows the progress UI and hides the login form.
     */

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    @Override
    public void showProgress(
            final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
            showLoginForm(show, shortAnimTime);
            showProgressView(show, shortAnimTime);
        } else {
            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private void showProgressView(final boolean show, int shortAnimTime) {
        progressView.setVisibility(show ? View.VISIBLE : View.GONE);
        progressView.animate()
                .setDuration(shortAnimTime)
                .alpha(show ? 1 : 0)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        progressView.setVisibility(show ? View.VISIBLE : View.GONE);
                    }
                });
    }

    private void showLoginForm(final boolean show, int shortAnimTime) {
        loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        loginFormView.animate()
                .setDuration(shortAnimTime)
                .alpha(show ? 0 : 1)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.destroyView();
    }
}

