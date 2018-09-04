package alex.example.com.maximapp.ui.divisions;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.List;

import alex.example.com.maximapp.R;
import alex.example.com.maximapp.StartApplication;
import alex.example.com.maximapp.data.entity.Department;
import alex.example.com.maximapp.ui.BaseActivity;
import alex.example.com.maximapp.ui.login.LoginActivity;
import alex.example.com.maximapp.utils.SharedPreferenceHelper;

public class DivisionsActivity extends BaseActivity implements DivisionsContract.View {
    private DivisionsContract.Presenter mPresenter;
    private ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divisions);
        expandableListView = findViewById(R.id.expandableListView);

        initToolbar();
        mPresenter = new DivisionPresenter(new SharedPreferenceHelper(this),
                StartApplication.get(this).getService());
        mPresenter.bind(this);
        mPresenter.downloadDepartmentData();
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(), "posit " + groupPosition, Toast.LENGTH_SHORT).show();
                if (groupPosition != previousGroup)
                    expandableListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnExit:
                mPresenter.clearSavedAuthData();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void logOut() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setAdapter(List<Department> list) {
        expandableListView.setAdapter(new TreeViewListAdapter(this, list));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }


}
