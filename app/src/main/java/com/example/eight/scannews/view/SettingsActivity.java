package com.example.eight.scannews.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.example.eight.scannews.MainActivity;
import com.example.eight.scannews.R;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener
        //implements NavigationView.OnNavigationItemSelectedListener
{
    public boolean isSwitchEnable;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private ActionBar actionBar;
    private LinearLayout cleanCache;
    private LinearLayout about;
    private LinearLayout feedback;
    private LinearLayout wifiSetting;
    private Switch wifiSwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initView();
        actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        isSwitchEnable = wifiSwitch.isEnabled();
        sp = getSharedPreferences("setting", MODE_PRIVATE);

        Log.e("---------->", "onCreate: " + isSwitchEnable);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        cleanCache = (LinearLayout) findViewById(R.id.clean_cache);
        cleanCache.setOnClickListener(this);
        about = (LinearLayout) findViewById(R.id.about);
        about.setOnClickListener(this);
        feedback = (LinearLayout) findViewById(R.id.feedback);
        feedback.setOnClickListener(this);
        wifiSetting = (LinearLayout) findViewById(R.id.wifi_setting);
        wifiSetting.setOnClickListener(this);
        wifiSwitch = (Switch) findViewById(R.id.wifi_switch);
        wifiSwitch.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wifi_setting:
                wifiSwitch.setChecked(!isSwitchEnable);
                //Log.e("---------->", String.valueOf(isSwitchEnable));
                break;
            case R.id.clean_cache:
                Snackbar.make(cleanCache, "已清除缓存", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.about:
                //Snackbar.make(about, "敬请期待", Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                intent.putExtra("id", 1);
                startActivity(intent);
                break;
            case R.id.feedback:
                Snackbar.make(feedback, "敬请期待", Snackbar.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            isSwitchEnable = true;
        } else {
            isSwitchEnable = false;
        }
        //Log.e("------>---->", String.valueOf(isSwitchEnable));
    }
}


