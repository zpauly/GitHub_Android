package com.zpauly.githubapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.entity.response.issues.MilestoneBean;
import com.zpauly.githubapp.utils.TextUtil;
import com.zpauly.githubapp.view.ToolbarActivity;
import com.zpauly.githubapp.view.login.LoginActivity;
import com.zpauly.githubapp.view.viewholder.MilestoneViewHolder;

/**
 * Created by zpauly on 2016/10/11.
 */

public class MilestonesRecyclerViewAdapter extends LoadMoreRecyclerViewAdapter<MilestoneBean, MilestoneViewHolder> {
    private final String TAG = getClass().getName();

    public MilestonesRecyclerViewAdapter(Context context) {
        super(context);
    }

    @Override
    public MilestoneViewHolder createContentViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.item_recyclerview_milestone, parent, false);
        MilestoneViewHolder viewHolder = new MilestoneViewHolder(mView);
        return viewHolder;
    }

    @Override
    public void bindContentViewHolder(MilestoneViewHolder holder, int position) {
        MilestoneBean data = getData().get(position);
        int completeness;
        int open = data.getOpen_issues();
        int closed = data.getClosed_issues();
        holder.mTitleTV.setText(data.getTitle());
        if (data.getState().equals("closed")) {
            holder.mDueOnIV.setVisibility(View.GONE);
            holder.mDueOnTV.setVisibility(View.GONE);
        } else {
            holder.mDueOnIV.setVisibility(View.VISIBLE);
            holder.mDueOnTV.setVisibility(View.VISIBLE);
        }
        if (data.getDue_on() == null || data.getDue_on().equals("")) {
            holder.mDueOnIV.setVisibility(View.GONE);
            holder.mDueOnTV.setText(R.string.no_due_date);
        } else {
            holder.mDueOnIV.setVisibility(View.VISIBLE);
            holder.mDueOnTV.setText(TextUtil.timeConverter(data.getDue_on()));
        }
        holder.mUpdateOnTV.setText(TextUtil.timeConverter(data.getUpdated_at()));
        if (data.getDescription() == null || data.getDescription().equals("")) {
            holder.mDescTV.setVisibility(View.GONE);
        } else {
            holder.mDescTV.setText(data.getDescription());
        }
        if (data.getOpen_issues() == 0 && data.getClosed_issues() == 0) {
            completeness = 0;
        } else {
            completeness = (int) ((float) closed / (float) (open + closed)) * 100;
        }
        holder.mCompleteTV.setText(completeness + "%");
        holder.mOpenTV.setText(String.valueOf(open));
        holder.mClosedTV.setText(String.valueOf(closed));
        float progress = (float) closed / (float) (open + closed);
        int progressState = (int) (progress * holder.mCompletenessPB.getMax());
        if (progressState <= holder.mCompletenessPB.getMax()) {
            holder.mCompletenessPB.setProgress(progressState);
        } else {

        }
    }
}
