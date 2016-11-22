package com.zpauly.githubapp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.utils.SPUtil;
import com.zpauly.githubapp.view.home.HomeActivity;
import com.zpauly.githubapp.view.login.LoginActivity;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * Created by zpauly on 2016/11/5.
 */

public class SplashActivity extends AppCompatActivity {
    public static final long SPLASH_TIME = 1000;
    public static final int JUMP_TO_NEXT_ACTIVITY = 0;

    private SplashHandler handler = new SplashHandler(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler.sendEmptyMessageDelayed(JUMP_TO_NEXT_ACTIVITY, SPLASH_TIME);
    }

    private class SplashHandler extends Handler {
        private WeakReference<Context> contextWeakReference;

        public SplashHandler(Context context) {
            contextWeakReference = new WeakReference<Context>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == JUMP_TO_NEXT_ACTIVITY) {
                if (contextWeakReference.get() == null) {
                    return;
                }
                Context context = contextWeakReference.get();
                if (!SPUtil.getBoolean(context, Constants.LOCAL_CONFIGURATION, Constants.FIRST_USED, true)) {
                    Intent intent = new Intent();
                    intent.setClass(context, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent();
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
            super.handleMessage(msg);
        }
    }
}
