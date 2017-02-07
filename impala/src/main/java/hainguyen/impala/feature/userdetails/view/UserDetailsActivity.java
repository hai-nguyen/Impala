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
import hainguyen.impala.model.api.LoginResponse;
import hainguyen.impala.feature.base.BaseActivity;
import hainguyen.impala.feature.userdetails.presenter.UserDetailsPresenter;

public class UserDetailsActivity extends BaseActivity implements UserDetailsView {

    @Inject
    UserDetailsPresenter presenter;

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

    private LoginResponse user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setupViewComponent();
        presenter.setView(this);
        Intent intent = getIntent();

        populateUserDetails();
    }

    @OnClick(R.id.fab)
    void onActionButtonClicked(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show();

        this.finish();
    }

    @Override
    public void setupViewComponent() {
        ImpalaApplication.getInstance().getAppComponent().inject(this);
    }

    @Override
    public void populateUserDetails() {
        //Application Bus
        textViewWelcome.setText(String.format(textViewWelcome.getText().toString(),
                presenter.getUserDetails().fullName()));
        textViewDOB.setText(String.format(textViewDOB.getText().toString(),
                presenter.getUserDetails().dateOfBirth()));
        textViewAddress.setText(String.format(textViewAddress.getText().toString(),
                presenter.getUserDetails().address()));
        textViewGender.setText(String.format(textViewGender.getText().toString(),
                presenter.getUserDetails().gender()));
        textViewPhone.setText(String.format(textViewPhone.getText().toString(),
                presenter.getUserDetails().phone()));
    }
}
