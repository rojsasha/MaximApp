package alex.example.com.maximapp.ui.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Toast;

import alex.example.com.maximapp.R;
import alex.example.com.maximapp.StartApplication;
import alex.example.com.maximapp.ui.BaseActivity;
import alex.example.com.maximapp.ui.divisions.DivisionsActivity;
import alex.example.com.maximapp.utils.SharedPreferenceHelper;
import alex.example.com.maximapp.utils.StringResourses;

public class LoginActivity extends BaseActivity implements LoginContract.View, View.OnClickListener {
    private AppCompatButton mBtnLogin;
    private AppCompatEditText mEtLogin, mEtPassword;
    private LoginContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mBtnLogin = findViewById(R.id.btnLogin);
        mEtLogin = findViewById(R.id.etLogin);
        mEtPassword = findViewById(R.id.etPassword);

        mPresenter = new LoginPresenter(new StringResourses(this),
                StartApplication.get(this).getService(), new SharedPreferenceHelper(this));
        mPresenter.bind(this);

        mPresenter.checkSavedAuthData();

        mBtnLogin.setOnClickListener(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }

    @Override
    public void onClick(View view) {
        mPresenter.checkLoginPassword(mEtLogin.getText().toString(), mEtPassword.getText().toString());
    }

    @Override
    public void etSetError(String message) {
        mEtLogin.setError(message);
    }

    @Override
    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startActivity() {
        startActivity(new Intent(this, DivisionsActivity.class));
        finish();
    }
}
