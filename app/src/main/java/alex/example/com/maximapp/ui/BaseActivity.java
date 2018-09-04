package alex.example.com.maximapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import alex.example.com.maximapp.R;

public abstract class BaseActivity extends AppCompatActivity {
    Toolbar mToolbar;
    protected void initToolbar(){
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(R.string.depatr);
    }

}
