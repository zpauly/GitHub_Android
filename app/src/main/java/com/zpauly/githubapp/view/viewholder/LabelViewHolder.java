package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseRecyclerViewViewHolder;

import butterknife.BindView;

/**
 * Created by zpauly on 2016/10/13.
 */

public class LabelViewHolder extends BaseRecyclerViewViewHolder {
    @BindView(R.id.label_item_layout) public LinearLayout mLayout;

    @BindView(R.id.label_item_label_card) public CardView mLabelCard;

    @BindView(R.id.label_item_name_TV) public AppCompatTextView mNameTV;

    @BindView(R.id.label_item_edit_IV) public ImageView mEditIV;

    public LabelViewHolder(View itemView) {
        super(itemView);
    }
}
