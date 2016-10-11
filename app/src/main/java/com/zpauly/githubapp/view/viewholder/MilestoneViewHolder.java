package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.zpauly.githubapp.R;

/**
 * Created by zpauly on 2016/10/11.
 */

public class MilestoneViewHolder extends RecyclerView.ViewHolder {
    public final LinearLayout mLayout;

    public final AppCompatTextView mTitleTV;

    public final ImageView mDueOnIV;

    public final AppCompatTextView mDueOnTV;

    public final AppCompatTextView mUpdateOnTV;

    public final AppCompatTextView mDescTV;

    public final ProgressBar mCompletenessPB;

    public final AppCompatTextView mCompleteTV;

    public final AppCompatTextView mOpenTV;

    public final AppCompatTextView mClosedTV;

    public MilestoneViewHolder(View itemView) {
        super(itemView);

        mLayout = (LinearLayout) itemView.findViewById(R.id.milestone_item_layout);
        mTitleTV = (AppCompatTextView) itemView.findViewById(R.id.milestone_item_title_TV);
        mDueOnIV = (ImageView) itemView.findViewById(R.id.milestone_item_due_on_IV);
        mDueOnTV = (AppCompatTextView) itemView.findViewById(R.id.milestone_item_due_on_TV);
        mUpdateOnTV = (AppCompatTextView) itemView.findViewById(R.id.milestone_item_update_on_TV);
        mDescTV = (AppCompatTextView) itemView.findViewById(R.id.milestone_item_description_TV);
        mCompletenessPB = (ProgressBar) itemView.findViewById(R.id.milestone_item_completeness_PB);
        mCompleteTV = (AppCompatTextView) itemView.findViewById(R.id.milestone_item_complete_TV);
        mOpenTV = (AppCompatTextView) itemView.findViewById(R.id.milestone_item_open_TV);
        mClosedTV = (AppCompatTextView) itemView.findViewById(R.id.milestone_item_closed_TV);
    }
}
