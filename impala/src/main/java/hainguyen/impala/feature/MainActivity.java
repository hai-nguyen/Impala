package hainguyen.impala.feature;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hainguyen.impala.R;
import hainguyen.impala.feature.login.view.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textViewTest)
    TextView textViewTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonTest)
    public void onButtonTestClicked() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
