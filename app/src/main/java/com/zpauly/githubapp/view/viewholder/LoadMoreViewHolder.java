package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ProgressBar;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseRecyclerViewViewHolder;

import butterknife.BindView;

/**
 * Created by zpauly on 16-7-28.
 */

public class LoadMoreViewHolder extends BaseRecyclerViewViewHolder {
    @BindView(R.id.load_more_tip) public AppCompatTextView mLoadTV;

    @BindView(R.id.load_more_progressbar) public ProgressBar mLoadPB;

    public LoadMoreViewHolder(View itemView) {
        super(itemView);
    }
}
