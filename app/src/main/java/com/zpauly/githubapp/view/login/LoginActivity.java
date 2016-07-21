package com.zpauly.githubapp.view.login;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseActivity;
import com.zpauly.githubapp.db.UserDao;
import com.zpauly.githubapp.entity.response.AuthenticatedUserBean;
import com.zpauly.githubapp.presenter.login.LoginContract;
import com.zpauly.githubapp.presenter.login.LoginPresenter;
import com.zpauly.githubapp.utils.AuthUtil;
import com.zpauly.githubapp.utils.LanguageUtil;
import com.zpauly.githubapp.utils.SPUtil;
import com.zpauly.githubapp.view.home.HomeActivity;

public class LoginActivity extends BaseActivity implements LoginContract.View {
    private LoginContract.Presenter mPresenter;

    private static final int SELECT_SIMPLIFIED_CHINESE = 0;
    private static final int SELECT_ENGLISH = 1;

    private LinearLayout mLanguageLayout;
    private LinearLayout mLoginLayout;
    private AppCompatCheckBox mSimplifiedChineseCB;
    private AppCompatCheckBox mEnglishCB;
    private AppCompatButton mLanguageOkBTN;
    private AppCompatButton mLoginBTN;
    private TextInputEditText mUsernameET;
    private TextInputEditText mPasswordET;
    private TextInputLayout mUsernameLayout;
    private TextInputLayout mPasswordLayout;

    private String username;
    private String password;

    private MaterialDialog loadingDialog;

    private boolean isLanguageSetted = false;
    private int languageChoice = -1;

    private boolean isFirstUsed = false;

    @Override
    protected void onStop() {
        if (mPresenter != null) {
            mPresenter.stop();
        }
        super.onStop();
    }

    @Override
    public void initViews() {
        if (SPUtil.getString(this, Constants.USER_INFO, Constants.USER_AUTH, null) != null) {
            Intent intent = new Intent();
            intent.setClass(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
        isFirstUsed = SPUtil.getBoolean(this, Constants.LOCAL_CONFIGURATION, Constants.FIRST_USED, true);

        new LoginPresenter(this, this);
        mPresenter.start();

        mLanguageLayout = (LinearLayout) findViewById(R.id.language_layout);
        mLoginLayout = (LinearLayout) findViewById(R.id.login_layout);
        mSimplifiedChineseCB = (AppCompatCheckBox) findViewById(R.id.language_simplified_chinese_cb);
        mEnglishCB = (AppCompatCheckBox) findViewById(R.id.language_english_cb);
        mLanguageOkBTN = (AppCompatButton) findViewById(R.id.language_ok_btn);
        mLoginBTN = (AppCompatButton) findViewById(R.id.login_sign_in_btn);
        mUsernameET = (TextInputEditText) findViewById(R.id.login_username_et);
        mPasswordET = (TextInputEditText) findViewById(R.id.login_password_et);
        mUsernameLayout = (TextInputLayout) findViewById(R.id.login_username_layout);
        mPasswordLayout = (TextInputLayout) findViewById(R.id.login_password_layout);

        setupCheckBoxs();

        setupButtons();

        if (!isFirstUsed) {
            mLanguageLayout.setVisibility(View.GONE);
            mLoginLayout.setVisibility(View.VISIBLE);
            resetLoginView();
        }
    }

    @Override
    public void initContent() {
        setContentView(R.layout.activity_login);
    }

    private void setupCheckBoxs() {
        mSimplifiedChineseCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isLanguageSetted = true;
                if (isChecked)
                    mEnglishCB.setChecked(false);
                languageChoice = SELECT_SIMPLIFIED_CHINESE;
            }
        });
        mEnglishCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isLanguageSetted = true;
                if (isChecked)
                    mSimplifiedChineseCB.setChecked(false);
                languageChoice = SELECT_ENGLISH;
            }
        });
    }

    private void setupButtons() {
        mLanguageOkBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLanguageSetted) {
                    Snackbar.make(v, R.string.please_choose, Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (languageChoice == SELECT_SIMPLIFIED_CHINESE) {
                    LanguageUtil.setLanguageToChinese(LoginActivity.this);
                } else if (languageChoice == SELECT_ENGLISH) {
                    LanguageUtil.setLanguageToEnglish(LoginActivity.this);
                }
                ViewPropertyAnimator.animate(mLanguageLayout)
                        .alpha(0f)
                        .setDuration(1000)
                        .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        mLoginLayout.setVisibility(View.VISIBLE);
                        resetLoginView();
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mLanguageLayout.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start();
            }
        });
        mLoginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = mUsernameET.getText().toString();
                password = mPasswordET.getText().toString();
                if (TextUtils.isEmpty(username)) {
                    mUsernameLayout.setError(getString(R.string.please_input_username));
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPasswordLayout.setError(getString(R.string.please_input_password));
                    return;
                }
                if (mPresenter != null) {
                    loadingDialog = new MaterialDialog.Builder(LoginActivity.this)
                            .progress(true, 0)
                            .title(R.string.please_wait)
                            .content(R.string.loading)
                            .build();
                    loadingDialog.show();
                    mPresenter.login();
                }
            }
        });
    }

    private void resetLoginView() {
        mUsernameLayout.setHint(getString(R.string.username));
        mPasswordLayout.setHint(getString(R.string.password));
        mLoginBTN.setText(R.string.login);
        mUsernameLayout.setErrorEnabled(true);
        mUsernameLayout.setErrorEnabled(true);
    }

    private void loadUser() {
        mPresenter.loadUserInfo();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void loginSuccess() {
        loadUser();
    }

    @Override
    public void loginFail() {
        loadingDialog.dismiss();
        Snackbar.make(getCurrentFocus(), "login fail", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void logining() {
        String username = mUsernameET.getText().toString();
        String password = mPasswordET.getText().toString();
        String auth = AuthUtil.generateAuth(username, password);
        SPUtil.putString(this, Constants.USER_INFO, Constants.USER_AUTH, auth);
        SPUtil.putString(this, Constants.USER_INFO, Constants.USER_USERNAME, username);
    }

    @Override
    public void loadUserInfo(AuthenticatedUserBean user) {
        UserDao.deleteUser();
        UserDao.insertUser(user);
    }

    @Override
    public void loadSuccess() {
        loadingDialog.dismiss();
        SPUtil.putBoolean(this, Constants.LOCAL_CONFIGURATION, Constants.FIRST_USED, false);
        Intent intent = new Intent();
        intent.setClass(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loadFail() {
        loadingDialog.dismiss();
        Snackbar.make(getCurrentFocus(), "login fail", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public String getAuth() {
        String username = mUsernameET.getText().toString();
        String password = mPasswordET.getText().toString();
        String auth = AuthUtil.generateAuth(username, password);
        return auth;
    }
}
