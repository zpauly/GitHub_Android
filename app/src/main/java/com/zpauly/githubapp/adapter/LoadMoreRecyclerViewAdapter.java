package com.zpauly.githubapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseAdapter;
import com.zpauly.githubapp.view.viewholder.LoadMoreViewHolder;

/**
 * Created by zpauly on 16-7-28.
 */

public abstract class LoadMoreRecyclerViewAdapter<D, VH extends RecyclerView.ViewHolder>
        extends BaseAdapter<D, RecyclerView.ViewHolder> {
    private final String TAG = getClass().getName();

    public static final int LOAD_MORE_VIEW_TYPE = 10000;

    protected Context mContext;

    private boolean hasMoreData = true;
    private boolean flag = false;

    public LoadMoreRecyclerViewAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == LOAD_MORE_VIEW_TYPE) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_loadmore, parent, false);
            return new LoadMoreViewHolder(view);
        } else {
            return createContentViewHolder(parent, viewType);
        }
    }

    public abstract VH createContentViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == getItemCount() - 1) {
            LoadMoreViewHolder loadMoreViewHolder = (LoadMoreViewHolder) holder;
            loadMoreViewHolder.itemView.setVisibility(View.GONE);
            if (!flag) {
                return;
            }
            if (hasMoreData) {
                loadMoreViewHolder.itemView.setVisibility(View.VISIBLE);
                loadMoreViewHolder.mLoadPB.setVisibility(View.VISIBLE);
                loadMoreViewHolder.mLoadTV.setVisibility(View.GONE);
            } else {
                loadMoreViewHolder.itemView.setVisibility(View.VISIBLE);
                loadMoreViewHolder.mLoadPB.setVisibility(View.GONE);
                loadMoreViewHolder.mLoadTV.setVisibility(View.VISIBLE);
            }
        } else {
            VH viewholder = (VH) holder;
            bindContentViewHolder(viewholder, position);
        }
    }

    public abstract void bindContentViewHolder(VH holder, int position);

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return LOAD_MORE_VIEW_TYPE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 1;
    }


    public void hideLoadingView() {
        flag = false;
        notifyDataSetChanged();
    }

    public void setHasLoading(boolean hasMoreData) {
        this.hasMoreData = hasMoreData;
        flag = true;
//        notifyDataSetChanged();
    }

    public boolean isHasMoreData() {
        return hasMoreData;
    }
}
