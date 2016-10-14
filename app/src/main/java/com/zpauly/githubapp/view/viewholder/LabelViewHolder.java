package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;

/**
 * Created by zpauly on 2016/10/13.
 */

public class LabelViewHolder extends RecyclerView.ViewHolder {
    public final LinearLayout mLayout;

    public final CardView mLabelCard;

    public final AppCompatTextView mNameTV;

    public final ImageView mEditIV;

    public LabelViewHolder(View itemView) {
        super(itemView);

        mLayout = (LinearLayout) itemView.findViewById(R.id.label_item_layout);
        mLabelCard = (CardView) itemView.findViewById(R.id.label_item_label_card);
        mNameTV = (AppCompatTextView) itemView.findViewById(R.id.label_item_name_TV);
        mEditIV = (ImageView) itemView.findViewById(R.id.label_item_edit_IV);
    }
}
