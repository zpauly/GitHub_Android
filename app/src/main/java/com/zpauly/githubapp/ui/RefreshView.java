package com.zpauly.githubapp.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.zpauly.githubapp.R;

/**
 * Created by zpauly on 16/8/25.
 */
public class RefreshView extends LinearLayout {
    public interface OnRefreshStateListener {
        void beforeRefreshing();

        void onRefreshSuccess();

        void onRefreshFail();
    }

    private final String TAG = getClass().getName();

    private AppCompatTextView mRefreshTV;
    private ProgressBar mRefreshPB;

    private boolean isRefreshSuccess = false;
    private boolean isRefreshing = false;

    private OnRefreshStateListener mOnRefreshStateListener;

    public RefreshView(Context context) {
        super(context);
        init();
    }

    public RefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public RefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RefreshView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        View view = inflate(getContext(), R.layout.refresh_view, null);
        LinearLayoutCompat.LayoutParams layoutParams =
                new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mRefreshPB = (ProgressBar) view.findViewById(R.id.refresh_PB);
        view.setClickable(true);
        mRefreshTV = (AppCompatTextView) view.findViewById(R.id.refresh_TV);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isRefreshing) {
                    startRefresh();
                }
            }
        });
        addView(view, layoutParams);
    }

    public void startRefresh() {
        mRefreshTV.setVisibility(GONE);
        mRefreshPB.setVisibility(VISIBLE);
        if (mOnRefreshStateListener != null) {
            mOnRefreshStateListener.beforeRefreshing();
        }
        isRefreshing = true;
    }

    public void refreshSuccess() {
        isRefreshSuccess = true;
        isRefreshing = false;
        this.setVisibility(GONE);
        if (mOnRefreshStateListener != null) {
            mOnRefreshStateListener.onRefreshSuccess();
        }
    }

    public void refreshFail() {
        isRefreshSuccess = false;
        isRefreshing = false;
        mRefreshPB.setVisibility(GONE);
        mRefreshTV.setVisibility(VISIBLE);
        if (mOnRefreshStateListener != null) {
            mOnRefreshStateListener.onRefreshFail();
        }
    }

    public void setOnRefreshStateListener(OnRefreshStateListener listener) {
        this.mOnRefreshStateListener = listener;
    }

    public boolean isRefreshSuccess() {
        return isRefreshSuccess;
    }
}
