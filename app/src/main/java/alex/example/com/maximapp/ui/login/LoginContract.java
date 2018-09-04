package alex.example.com.maximapp.ui.login;


import alex.example.com.maximapp.ui.Lifecycle;

public interface LoginContract {
    interface View {
        void etSetError(String message);
        void toast(String msg);
        void startActivity();


    }

    interface Presenter extends Lifecycle<View> {
        void checkLoginPassword(String login, String password);
        void checkSavedAuthData();

    }
}
