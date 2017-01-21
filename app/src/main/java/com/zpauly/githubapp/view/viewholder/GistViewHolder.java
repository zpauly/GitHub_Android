package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseRecyclerViewViewHolder;

import butterknife.BindView;

/**
 * Created by zpauly on 16-8-5.
 */

public class GistViewHolder extends BaseRecyclerViewViewHolder {
    @BindView(R.id.gist_item_layout) public LinearLayout mLayout;

    @BindView(R.id.gist_item_avatar_IV) public ImageView mAvatarIV;

    @BindView(R.id.gist_item_title_TV) public AppCompatTextView mTitleTV;

    @BindView(R.id.gist_item_time_TV) public AppCompatTextView mTimeTV;

    @BindView(R.id.gist_item_desc_TV) public AppCompatTextView mDescTV;

    public GistViewHolder(View itemView) {
        super(itemView);
    }
}
