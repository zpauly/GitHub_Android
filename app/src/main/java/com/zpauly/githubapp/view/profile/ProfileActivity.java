package com.zpauly.githubapp.view.profile;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.view.ToolbarActivity;

/**
 * Created by zpauly on 16-7-30.
 */

public class ProfileActivity extends ToolbarActivity {

    @Override
    public void initViews() {
        setContent(new ProfileFragment());
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(R.string.profile);
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public static void launchActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ProfileActivity.class);
        context.startActivity(intent);
    }
}
