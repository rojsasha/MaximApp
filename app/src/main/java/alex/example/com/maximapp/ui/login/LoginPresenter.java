package alex.example.com.maximapp.ui.login;

import android.support.annotation.NonNull;

import alex.example.com.maximapp.R;
import alex.example.com.maximapp.data.ConnectionInterface;
import alex.example.com.maximapp.data.entity.AuthModel;
import alex.example.com.maximapp.utils.SharedPreferenceHelper;
import alex.example.com.maximapp.utils.StringResourses;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View mView;
    private StringResourses mStringResourses;
    private ConnectionInterface mService;
    private SharedPreferenceHelper mSharedPreferenceHelper;
    private String login;
    private String password;


    public LoginPresenter(StringResourses stringResourses, ConnectionInterface service, SharedPreferenceHelper sharedPreferenceHelper) {
        mStringResourses = stringResourses;
        mService = service;
        mSharedPreferenceHelper = sharedPreferenceHelper;
    }

    @Override
    public void bind(LoginContract.View view) {
        mView = view;

    }

    @Override
    public void unbind() {
        mView = null;
    }

    @Override
    public void checkLoginPassword(String login, String password) {

        if (login.length() == 0 || password.length() == 0) {
            mView.etSetError(mStringResourses.getString(R.string.auth_length_error));
        } else {
            this.login = login;
            this.password = password;
            getAuth();
        }
    }

    @Override
    public void checkSavedAuthData() {
        if (mSharedPreferenceHelper.isSavedAuthData())
            mView.startActivity();
    }

    private void getAuth() {
        mView.toast("auth");
        mService.checkAuth(login, password)
                .enqueue(new Callback<AuthModel>() {
                    @Override
                    public void onResponse(@NonNull Call<AuthModel> call, @NonNull Response<AuthModel> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            checkAuthResponse(response.body());
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<AuthModel> call, @NonNull Throwable t) {
                        mView.toast(t.getLocalizedMessage());
                    }
                });
    }

    private void checkAuthResponse(AuthModel body) {
        if (body.getSuccess()) {
            mSharedPreferenceHelper.saveAuthData(login, password);
            mView.startActivity();
        } else {
            mView.etSetError(body.getMessage());
        }
    }
}
