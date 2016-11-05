package com.zpauly.githubapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.utils.SPUtil;
import com.zpauly.githubapp.view.home.HomeActivity;
import com.zpauly.githubapp.view.login.LoginActivity;

import java.util.concurrent.TimeUnit;

/**
 * Created by zpauly on 2016/11/5.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemClock.sleep(TimeUnit.SECONDS.toMillis(2));
        if (!SPUtil.getBoolean(this, Constants.LOCAL_CONFIGURATION, Constants.FIRST_USED, true)) {
            Intent intent = new Intent();
            intent.setClass(this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent();
            intent.setClass(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
