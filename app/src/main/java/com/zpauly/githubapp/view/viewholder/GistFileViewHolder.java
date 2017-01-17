package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseRecyclerViewViewHolder;

import butterknife.BindView;

/**
 * Created by zpauly on 16-8-6.
 */

public class GistFileViewHolder extends BaseRecyclerViewViewHolder {
    @BindView(R.id.gist_file_layout) public LinearLayout mLayout;

    @BindView(R.id.gist_file_TV) public AppCompatTextView mNameTV;

    public GistFileViewHolder(View itemView) {
        super(itemView);
    }
}
