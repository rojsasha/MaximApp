package alex.example.com.maximapp;

import android.app.Application;
import android.content.Context;

import alex.example.com.maximapp.data.ConnectionInterface;
import alex.example.com.maximapp.data.RetrofitBuilder;

public class StartApplication extends Application {
    private ConnectionInterface mService;

    @Override
    public void onCreate() {
        super.onCreate();
        mService = RetrofitBuilder.retrofitBuilder();
    }

    public static StartApplication get(Context context) {
        return (StartApplication) context.getApplicationContext();
    }

    public ConnectionInterface getService() {
        return mService;
    }
}
