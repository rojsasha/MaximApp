package alex.example.com.maximapp.ui.divisions;

import java.util.List;

import alex.example.com.maximapp.data.entity.Department;
import alex.example.com.maximapp.ui.Lifecycle;

public interface DivisionsContract {
    interface View{
        void logOut();
        void onError(String msg);
        void setAdapter(List<Department> list);

    }

    interface Presenter extends Lifecycle<View>{
        void clearSavedAuthData();
        void downloadDepartmentData();
    }
}
