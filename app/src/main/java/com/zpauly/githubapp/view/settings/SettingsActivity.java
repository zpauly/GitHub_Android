package com.zpauly.githubapp.view.settings;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;

import com.afollestad.materialdialogs.MaterialDialog;
import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseApplication;
import com.zpauly.githubapp.utils.SPUtil;
import com.zpauly.githubapp.view.ToolbarActivity;
import com.zpauly.githubapp.view.dialog.AboutMeDialogFragment;
import com.zpauly.githubapp.view.dialog.OpenSourceComponentsDialog;

/**
 * Created by zpauly on 2016/10/23.
 */

public class SettingsActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    private AppCompatTextView mCodeStyleTV;
    private AppCompatTextView mAboutMeTV;
    private AppCompatTextView mOpenSourceComponentsTV;
    private SwitchCompat mNightSC;

    private MaterialDialog mCodeStyleDialog;

    @Override
    public void initViews() {
        mCodeStyleTV = (AppCompatTextView) findViewById(R.id.settings_code_style_TV);
        mAboutMeTV = (AppCompatTextView) findViewById(R.id.settings_about_me_TV);
        mOpenSourceComponentsTV = (AppCompatTextView) findViewById(R.id.settings_open_source_TV);
        mNightSC = (SwitchCompat) findViewById(R.id.night_theme_SC);

        setupCodeStyleDialog();
        setupViews();
    }

    @Override
    public void initContent() {
        super.initContent();
        setContent(R.layout.content_settings);
    }

    private void setupCodeStyleDialog() {
        int lastCodeStyle = SPUtil.getInt(this, Constants.LOCAL_CONFIGURATION, Constants.CODE_STYLE, 0);
        mCodeStyleDialog = new MaterialDialog.Builder(this)
                .title(R.string.choose_code_style)
                .items(Constants.codeStyleName)
                .itemsCallbackSingleChoice(lastCodeStyle, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        SPUtil.putInt(SettingsActivity.this, Constants.LOCAL_CONFIGURATION,
                                Constants.CODE_STYLE, which);
                        return true;
                    }
                })
                .positiveText(R.string.ok)
                .build();
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
        mCodeStyleTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCodeStyleDialog.show();
            }
        });
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
        if (BaseApplication.getDayNightMode() == BaseApplication.DAY_MODE) {
            mNightSC.setChecked(false);
        } else if (BaseApplication.getDayNightMode() == BaseApplication.NIGHT_MODE) {
            mNightSC.setChecked(true);
        }
        mNightSC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    BaseApplication.setDayNightMode(BaseApplication.NIGHT_MODE, getApplicationContext());
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    recreate();
                } else {
                    BaseApplication.setDayNightMode(BaseApplication.DAY_MODE, getApplicationContext());
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    recreate();
                }
            }
        });
    }

    private void createDialog(DialogFragment dialogFragment, String tag) {
        dialogFragment.show(getSupportFragmentManager(), tag);
    }

    public static void launchActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SettingsActivity.class);
        context.startActivity(intent);
    }
}
