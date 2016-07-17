package com.zpauly.githubapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.db.UserDao;
import com.zpauly.githubapp.db.UserModel;
import com.zpauly.githubapp.listener.OnNavItemClickListener;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16-6-9.
 */
public abstract class DrawerActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    private UserModel userInfo;

    protected DrawerLayout mDrawerLayout;
    protected NavigationView mNavigationView;

    protected CircleImageView mDrawerAvatar;
    protected TextView mDrawerName;
    protected TextView mDrawerEmail;

    private OnNavItemClickListener listener;

    protected void setOnNavItemClickListener(OnNavItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initNavDrawer();
    }

    private void getUserInfo() {
        userInfo = UserDao.queryUser();
    }

    private void initNavDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.nav_drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);

        View nav_header = mNavigationView.getHeaderView(0);

        mDrawerAvatar = (CircleImageView) nav_header.findViewById(R.id.drawer_avatar_IV);
        mDrawerName = (TextView) nav_header.findViewById(R.id.drawer_name_TV);
        mDrawerEmail = (TextView) nav_header.findViewById(R.id.drawer_email_TV);

        if (mToolbar != null) {
            Log.i(TAG, "not null");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
            setOnToolbarNavClickedListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            });

            initNavView();
        }
    }

    private void initNavView() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (listener != null) {
                    listener.onItemClick(item);
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
        getUserInfo();
        if (userInfo != null) {
            Glide.with(this)
                    .load(userInfo.getAvatar_url())
                    .centerCrop()
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mDrawerAvatar);
            mDrawerName.setText(userInfo.getName());
            mDrawerEmail.setText(userInfo.getEmail());
        }
    }

    @Override
    public void initContent() {
        setContentView(R.layout.drawer_layout);
    }
}
