package alex.example.com.maximapp.data;

import java.util.List;

import alex.example.com.maximapp.data.entity.AuthModel;
import alex.example.com.maximapp.data.entity.Department;
import alex.example.com.maximapp.data.entity.MainDepartmentModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static alex.example.com.maximapp.utils.AppConstants.AUTH_ENDPOINT;
import static alex.example.com.maximapp.utils.AppConstants.GETALL_ENDPOINT;

public interface ConnectionInterface {
    @GET(AUTH_ENDPOINT)
    Call<AuthModel> checkAuth(@Query("login") String login,
                              @Query("password") String password);

    @GET(GETALL_ENDPOINT)
    Call<MainDepartmentModel> getDivisions(@Query("login") String login,
                                                 @Query("password") String password);
}
