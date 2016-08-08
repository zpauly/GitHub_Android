package com.zpauly.githubapp.view.gists;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.GistFileRecyclerViewAdapter;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.entity.response.gists.GistFileBean;
import com.zpauly.githubapp.entity.response.gists.GistsBean;
import com.zpauly.githubapp.utils.TextUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16-8-6.
 */

public class GistContentFragment extends BaseFragment {
    private final String TAG = getClass().getName();

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
    protected void initViews(View view) {
        getAttrs();

        mAvatarIV = (CircleImageView) view.findViewById(R.id.gist_content_avatar_IV);
        mLoginTV = (AppCompatTextView) view.findViewById(R.id.gist_content_login_TV);
        mUpdatedTV = (AppCompatTextView) view.findViewById(R.id.gist_content_updated_at_TV);
        mCreatedTV = (AppCompatTextView) view.findViewById(R.id.gist_content_created_at_TV);
        mDescTV = (AppCompatTextView) view.findViewById(R.id.gist_content_desc_TV);
        mFilesRV = (RecyclerView) view.findViewById(R.id.gist_content_RV);

        setupRecyclerView();

        setViews();
    }

    private void setupRecyclerView() {
        mFileAdapter = new GistFileRecyclerViewAdapter(getContext());
        mFilesRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mFilesRV.setAdapter(mFileAdapter);
    }

    private void setViews() {
        Glide.with(this)
                .load(Uri.parse(bean.getOwner().getAvatar_url()))
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mAvatarIV);
        mLoginTV.setText(bean.getOwner().getLogin());
        mUpdatedTV.setText(TextUtil.timeConverter(bean.getUpdated_at()));
        mCreatedTV.setText(TextUtil.timeConverter(bean.getCreated_at()));
        mDescTV.setText(bean.getDescription());

        mFileAdapter.addAllData(list);
    }

    private void getAttrs() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            bean = (GistsBean) bundle.get(GistContentActivity.GIST_BUNDLE);
            list = bundle.getParcelableArrayList(GistContentActivity.FILE_LIST_BUNDLE);
        }
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_gist_content, container, false);
    }
}
