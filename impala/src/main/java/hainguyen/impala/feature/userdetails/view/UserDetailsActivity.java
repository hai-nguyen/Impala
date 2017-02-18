package hainguyen.impala.feature.userdetails.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hainguyen.impala.R;
import hainguyen.impala.application.ImpalaApplication;
import hainguyen.impala.feature.base.BaseActivity;
import hainguyen.impala.feature.login.view.LoginActivity;
import hainguyen.impala.feature.userdetails.presenter.UserDetailsPresenter;
import hainguyen.impala.model.api.LoginResponse;

public class UserDetailsActivity extends BaseActivity implements UserDetailsView {

    @Inject
    UserDetailsPresenter detailsPresenter;

    @BindView(R.id.textViewWelcome)
    TextView textViewWelcome;

    @BindView(R.id.textViewDateOfBirth)
    TextView textViewDOB;

    @BindView(R.id.textViewAddress)
    TextView textViewAddress;

    @BindView(R.id.textViewGender)
    TextView textViewGender;

    @BindView(R.id.textViewPhone)
    TextView textViewPhone;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setupViewComponent();
        detailsPresenter.setView(this);
        detailsPresenter.getUserDetails();
    }

    @OnClick(R.id.fab)
    void onActionButtonClicked(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show();

        backToLoginScreen();
    }

    @Override
    public void setupViewComponent() {
        ImpalaApplication.getInstance().getUserComponent().inject(this);
    }

    @Override
    public void populateUserDetails(LoginResponse userDetails) {
        //Application Bus
        textViewWelcome.setText(String.format(textViewWelcome.getText().toString(),
                userDetails.fullName()));
        textViewDOB.setText(String.format(textViewDOB.getText().toString(),
                userDetails.dateOfBirth()));
        textViewAddress.setText(String.format(textViewAddress.getText().toString(),
                userDetails.address()));
        textViewGender.setText(String.format(textViewGender.getText().toString(),
                userDetails.gender()));
        textViewPhone.setText(String.format(textViewPhone.getText().toString(),
                userDetails.phone()));
    }

    @Override
    public void backToLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        detailsPresenter.releaseUserScope();
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detailsPresenter.destroyView();
    }
}
