package com.zpauly.githubapp.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16-8-4.
 */

public abstract class BaseAdapter<D, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected Context mContext;
    protected List<D> mData;

    private OnItemClickedListener mOnItemClickedListener;

    public interface OnItemClickedListener {
        void onItemClicked(View view, int position);
    }

    protected OnItemClickedListener getItemClickedListener() {
        return mOnItemClickedListener;
    }

    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        mOnItemClickedListener = onItemClickedListener;
    }

    public BaseAdapter(Context context) {
        mContext = context;
        mData = new ArrayList<>();
    }

    public boolean addData(D d) {
        boolean result = mData.add(d);
        if (result) {
            notifyDataSetChanged();
        }
        return result;
    }

    public boolean addAllData(List<D> list) {
        boolean result = mData.addAll(list);
        if (result) {
            notifyDataSetChanged();
        }
        return result;
    }

    protected int getDataCount() {
        return mData.size();
    }

    public boolean swapAllData(List<D> list) {
        mData.clear();
        return addAllData(list);
    }

    protected Context getContext() {
        return mContext;
    }

    protected List<D> getData() {
        return mData;
    }

    @Override
    public int getItemCount() {
        return getDataCount();
    }
}
