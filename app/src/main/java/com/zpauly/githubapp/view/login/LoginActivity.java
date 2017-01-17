package com.zpauly.githubapp.view.login;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.umeng.analytics.MobclickAgent;
import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseActivity;
import com.zpauly.githubapp.entity.response.AppAuthorizationBean;
import com.zpauly.githubapp.entity.response.AuthenticatedUserBean;
import com.zpauly.githubapp.presenter.login.LoginContract;
import com.zpauly.githubapp.presenter.login.LoginPresenter;
import com.zpauly.githubapp.utils.AuthUtil;
import com.zpauly.githubapp.utils.SPUtil;
import com.zpauly.githubapp.view.home.HomeActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.View {
    private LoginContract.Presenter mPresenter;

    @BindView(R.id.login_layout) public LinearLayout mLoginLayout;
    @BindView(R.id.login_sign_in_btn) public AppCompatButton mLoginBTN;
    @BindView(R.id.login_username_et) public TextInputEditText mUsernameET;
    @BindView(R.id.login_password_et) public TextInputEditText mPasswordET;
    @BindView(R.id.login_username_layout) public TextInputLayout mUsernameLayout;
    @BindView(R.id.login_password_layout) public TextInputLayout mPasswordLayout;

    private String username;
    private String password;

    private MaterialDialog loadingDialog;

    @Override
    protected void onStop() {
        if (mPresenter != null) {
            mPresenter.stop();
        }
        super.onStop();
    }

    @Override
    public void initViews() {
        new LoginPresenter(this, this);
        mPresenter.start();

        mUsernameLayout.setHint(getString(R.string.username));
        mPasswordLayout.setHint(getString(R.string.password));
        mLoginBTN.setText(R.string.login);
        mUsernameLayout.setErrorEnabled(true);
        mUsernameLayout.setErrorEnabled(true);
    }

    @Override
    public void initContent() {
        setContentView(R.layout.activity_login);
    }

    @OnClick(R.id.login_sign_in_btn)
    public void onLoginButtonClick() {
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
                    .cancelable(false)
                    .title(R.string.please_wait)
                    .content(R.string.loading)
                    .build();
            loadingDialog.show();
            mPresenter.login();
        }
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
    public void logining(AppAuthorizationBean appAuthorizationBean) {
        String username = mUsernameET.getText().toString();
        String password = mPasswordET.getText().toString();
        String auth = AuthUtil.generateAuth(username, password);
        SPUtil.putString(this, Constants.USER_INFO, Constants.USER_AUTH, auth);
        SPUtil.putString(this, Constants.USER_INFO, Constants.USER_USERNAME, username);
    }

    @Override
    public void loadUserInfo(AuthenticatedUserBean user) {
        SPUtil.putString(this, Constants.USER_INFO, Constants.USER_EMAIL, user.getEmail());
        SPUtil.putString(this, Constants.USER_INFO, Constants.USER_AVATAR, user.getAvatar_url());
        SPUtil.putString(this, Constants.USER_INFO, Constants.USER_LOGIN, user.getLogin());
        MobclickAgent.onProfileSignIn(user.getLogin());
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
