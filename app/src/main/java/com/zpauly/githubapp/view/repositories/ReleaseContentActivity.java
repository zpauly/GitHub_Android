package com.zpauly.githubapp.view.repositories;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.entity.response.repos.ReleaseBean;
import com.zpauly.githubapp.utils.ImageUtil;
import com.zpauly.githubapp.utils.TextUtil;
import com.zpauly.githubapp.view.ToolbarActivity;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16/9/25.
 */

public class ReleaseContentActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    public static final String RELEASE = "RELEASE";

    private ReleaseBean releaseBean;

    @BindView(R.id.release_avatar_IV) public CircleImageView mAvatarIV;
    @BindView(R.id.release_username_TV) public AppCompatTextView mUsernameTV;
    @BindView(R.id.release_tag_TV) public AppCompatTextView mTagTV;
    @BindView(R.id.release_time_TV) public AppCompatTextView mTimeTV;
    @BindView(R.id.release_body_TV) public AppCompatTextView mBodyTV;

    @Override
    public void initViews() {
        if (releaseBean != null) {
            mUsernameTV.setText(releaseBean.getAuthor().getLogin());
            mTagTV.setText(releaseBean.getTag_name());
            mTimeTV.setText(TextUtil.timeConverter(releaseBean.getCreated_at()));
            mBodyTV.setText(releaseBean.getBody());
            ImageUtil.loadAvatarImageFromUrl(this, releaseBean.getAuthor().getAvatar_url(),
                    mAvatarIV);
        }
    }

    @Override
    public void initContent() {
        super.initContent();
        setContent(R.layout.content_release_content);
    }

    @Override
    protected void getParams() {
        releaseBean = getIntent().getParcelableExtra(RELEASE);
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(releaseBean.getName());
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public static void launchActivity(Context context, ReleaseBean releaseBean) {
        Intent intent = new Intent();
        intent.putExtra(RELEASE, releaseBean);
        intent.setClass(context, ReleaseContentActivity.class);
        context.startActivity(intent);
    }
}
