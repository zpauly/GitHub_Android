package com.zpauly.githubapp.view.settings;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.view.ToolbarActivity;
import com.zpauly.githubapp.view.dialog.AboutMeDialogFragment;
import com.zpauly.githubapp.view.dialog.OpenSourceComponentsDialog;

/**
 * Created by zpauly on 2016/10/23.
 */

public class SettingsActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    private AppCompatTextView mAboutMeTV;
    private AppCompatTextView mOpenSourceComponentsTV;

    @Override
    public void initViews() {
        setContent(R.layout.content_settings);

        mAboutMeTV = (AppCompatTextView) findViewById(R.id.settings_about_me_TV);
        mOpenSourceComponentsTV = (AppCompatTextView) findViewById(R.id.settings_open_source_TV);

        setupViews();
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(R.string.settings);
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setupViews() {
        mAboutMeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog(new AboutMeDialogFragment(), getString(R.string.contract_me));
            }
        });
        mOpenSourceComponentsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog(new OpenSourceComponentsDialog(), getString(R.string.open_source_components));
            }
        });
    }

    private void createDialog(DialogFragment dialogFragment, String tag) {
        DialogFragment dialog = dialogFragment;
        dialogFragment.show(getSupportFragmentManager(), tag);
    }

    public static void launchActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SettingsActivity.class);
        context.startActivity(intent);
    }
}
