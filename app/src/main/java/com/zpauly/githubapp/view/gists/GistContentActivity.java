package com.zpauly.githubapp.view.gists;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.GistFileRecyclerViewAdapter;
import com.zpauly.githubapp.entity.response.gists.GistFileBean;
import com.zpauly.githubapp.entity.response.gists.GistsBean;
import com.zpauly.githubapp.utils.ImageUtil;
import com.zpauly.githubapp.utils.TextUtil;
import com.zpauly.githubapp.view.ToolbarActivity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16-8-6.
 */

public class GistContentActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    public static final String GIST_BUNDLE = "GIST_BUNDLE";
    public static final String FILE_LIST_BUNDLE = "FILE_LIST_BUNDLE";

    private CircleImageView mAvatarIV;
    private AppCompatTextView mLoginTV;
    private AppCompatTextView mUpdatedTV;
    private AppCompatTextView mCreatedTV;
    private AppCompatTextView mDescTV;
    private RecyclerView mFilesRV;

    private GistFileRecyclerViewAdapter mFileAdapter;

    private GistsBean bean;
    private List<GistFileBean> list;

    @Override
    public void initViews() {
        getParams();

        mAvatarIV = (CircleImageView) findViewById(R.id.gist_content_avatar_IV);
        mLoginTV = (AppCompatTextView) findViewById(R.id.gist_content_login_TV);
        mUpdatedTV = (AppCompatTextView) findViewById(R.id.gist_content_updated_at_TV);
        mCreatedTV = (AppCompatTextView) findViewById(R.id.gist_content_created_at_TV);
        mDescTV = (AppCompatTextView) findViewById(R.id.gist_content_desc_TV);
        mFilesRV = (RecyclerView) findViewById(R.id.gist_content_RV);

        setupRecyclerView();

        setViews();
    }

    private void getParams() {
        bean = getIntent().getParcelableExtra(GIST_BUNDLE);
        list = getIntent().getParcelableArrayListExtra(FILE_LIST_BUNDLE);

        setContent(R.layout.content_gist_content);
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(bean.getId());
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public static void launchActivity(Context context, GistsBean bean, ArrayList<GistFileBean> list) {
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(FILE_LIST_BUNDLE, list);
        intent.putExtra(GIST_BUNDLE, bean);
        intent.setClass(context, GistContentActivity.class);
        context.startActivity(intent);
    }

    private void setupRecyclerView() {
        mFileAdapter = new GistFileRecyclerViewAdapter(this);
        mFilesRV.setLayoutManager(new LinearLayoutManager(this));
        mFilesRV.setAdapter(mFileAdapter);
    }

    private void setViews() {
        ImageUtil.loadAvatarImageFromUrl(this, bean.getOwner().getAvatar_url(), mAvatarIV);
        mLoginTV.setText(bean.getOwner().getLogin());
        mUpdatedTV.setText(TextUtil.timeConverter(bean.getUpdated_at()));
        mCreatedTV.setText(TextUtil.timeConverter(bean.getCreated_at()));
        mDescTV.setText(bean.getDescription());

        mFileAdapter.addAllData(list);
        mFileAdapter.setId(bean.getId());
    }
}
