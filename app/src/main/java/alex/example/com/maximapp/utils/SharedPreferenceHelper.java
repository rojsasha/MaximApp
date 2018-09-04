package alex.example.com.maximapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class SharedPreferenceHelper {
    private Context mContext;
    private SharedPreferences mSharedPreferences;
    private String LOGIN = "LOGIN";
    private String PASSWORD = "PASSWORD";
    private String DEFAULT_VALUE = "DEFAULT_VALUE";

    public SharedPreferenceHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences("apps", Context.MODE_PRIVATE);
    }

    public boolean isSavedAuthData() {
        ArrayList<String> list = getAuthData();
        return !list.get(0).equals(DEFAULT_VALUE) || !list.get(1).equals(DEFAULT_VALUE);
    }



    public ArrayList<String> getAuthData() {
        String login = mSharedPreferences.getString(LOGIN, DEFAULT_VALUE);
        String password = mSharedPreferences.getString(PASSWORD, DEFAULT_VALUE);
        ArrayList<String> list = new ArrayList<>();
        list.add(login);
        list.add(password);
        return list;
    }

    public void saveAuthData(String login, String password) {
        mSharedPreferences.edit().putString(LOGIN, login).putString(PASSWORD, password).apply();
    }

    public void clearAuthData(){
        mSharedPreferences.edit().clear().apply();
    }


}
