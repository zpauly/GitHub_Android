package com.zpauly.githubapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseAdapter;
import com.zpauly.githubapp.entity.response.issues.LabelBean;
import com.zpauly.githubapp.utils.ColorUtil;
import com.zpauly.githubapp.view.viewholder.LabelViewHolder;

/**
 * Created by zpauly on 2016/10/13.
 */

public class LabelsRecycelrViewAdapter extends BaseAdapter<LabelBean, LabelViewHolder> {
    private final String TAG = getClass().getName();

    private boolean isEditImageShow;

    public LabelsRecycelrViewAdapter(Context context) {
        super(context);
    }

    @Override
    public LabelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.item_recyclerview_label, parent, false);
        LabelViewHolder viewHolder = new LabelViewHolder(mView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LabelViewHolder holder, int position) {
        LabelBean data = getData().get(position);
        if (data == null)
            return;
        holder.mNameTV.setText(data.getName());
        holder.mLabelCard.setBackgroundResource(R.drawable.issue_label_bg);
        GradientDrawable bg = (GradientDrawable) holder.mLabelCard.getBackground();
        bg.setColor(Color.parseColor("#" + data.getColor()));
        holder.mNameTV.setTextColor(ColorUtil.computeTextColorFromBackgroundColor("#"
                + data.getColor()));
        if (isEditImageShow) {
            holder.mEditIV.setVisibility(View.VISIBLE);
        } else {
            holder.mEditIV.setVisibility(View.GONE);
        }
        holder.mEditIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setEditImageShow(boolean show) {
        this.isEditImageShow = show;
    }
}
