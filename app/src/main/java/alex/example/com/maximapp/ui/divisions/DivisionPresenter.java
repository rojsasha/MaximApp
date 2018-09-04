package alex.example.com.maximapp.ui.divisions;


import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import alex.example.com.maximapp.data.ConnectionInterface;
import alex.example.com.maximapp.data.entity.AuthModel;
import alex.example.com.maximapp.data.entity.Department;
import alex.example.com.maximapp.data.entity.MainDepartmentModel;
import alex.example.com.maximapp.utils.SharedPreferenceHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DivisionPresenter implements DivisionsContract.Presenter {
    private DivisionsContract.View mView;
    private SharedPreferenceHelper mSharedPreferenceHelper;
    private ConnectionInterface mService;
    private String login, password;

    DivisionPresenter(SharedPreferenceHelper sharedPreferenceHelper, ConnectionInterface service) {
        mService = service;
        mSharedPreferenceHelper = sharedPreferenceHelper;
        ArrayList<String> list = mSharedPreferenceHelper.getAuthData();
        login = list.get(0);
        password = list.get(1);

    }

    @Override
    public void bind(DivisionsContract.View view) {
        mView = view;
    }

    @Override
    public void unbind() {
        mView = null;
    }

    @Override
    public void clearSavedAuthData() {
        mSharedPreferenceHelper.clearAuthData();
        mView.logOut();

    }

    @Override
    public void downloadDepartmentData() {
        mService.getDivisions(login, password)
                .enqueue(new Callback<MainDepartmentModel>() {
                    @Override
                    public void onResponse(Call<MainDepartmentModel> call, Response<MainDepartmentModel> response) {
//                        mView.onError(String.valueOf(response.body().getDepartments().size()));
                        mView.setAdapter(response.body().getDepartments());
                    }

                    @Override
                    public void onFailure(Call<MainDepartmentModel> call, Throwable t) {

                    }
                });
    }
}
