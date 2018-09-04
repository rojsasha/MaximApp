package alex.example.com.maximapp.data;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static alex.example.com.maximapp.utils.AppConstants.BASE_URL;

public class RetrofitBuilder {
    private static ConnectionInterface mService = null;


    public static ConnectionInterface retrofitBuilder() {
        if (mService == null) {
            return new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOkHttpClient())
                    .build()
                    .create(ConnectionInterface.class);
        }
        return mService;

    }

    private static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder builder = chain.request()
                                .newBuilder()
                                .addHeader("Accept", "application/json;version=1");

                        return chain.proceed(builder.build());
                    }
                })
                .build();
    }
}
