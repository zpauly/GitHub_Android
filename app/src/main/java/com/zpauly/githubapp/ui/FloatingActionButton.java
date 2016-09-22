package com.zpauly.githubapp.ui;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

import com.nineoldandroids.view.ViewPropertyAnimator;
import com.zpauly.githubapp.listener.NestedScrollViewScrollListener;
import com.zpauly.githubapp.listener.RecyclerViewScrollListener;

/**
 * Created by zpauly on 16/9/7.
 */
public class FloatingActionButton extends android.support.design.widget.FloatingActionButton {
    private static final boolean SHOW = true;
    private static final boolean HIDE = false;

    private float mMarginBottom;

    private boolean buttonVisibleState = SHOW;

    public FloatingActionButton(Context context) {
        super(context);
    }

    public FloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        init();
    }

    private void init() {
        if (getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams layoutParams =
                    (ViewGroup.MarginLayoutParams) getLayoutParams();
            layoutParams.setMargins(layoutParams.leftMargin,
                    layoutParams.topMargin,
                    layoutParams.rightMargin,
                    layoutParams.bottomMargin);
            mMarginBottom = layoutParams.bottomMargin;
            requestLayout();
        }
    }

    private void buttonShowOrHide(final boolean show, final boolean force) {
        if (buttonVisibleState != show || force) {
            buttonVisibleState = show;
            int height = getHeight();
            if (height == 0 && !force) {
                ViewTreeObserver vto = getViewTreeObserver();
                if (vto.isAlive()) {
                    vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                        @Override
                        public boolean onPreDraw() {
                            ViewTreeObserver currentVto = getViewTreeObserver();
                            if (currentVto.isAlive()) {
                                currentVto.removeOnPreDrawListener(this);
                            }
                            buttonShowOrHide(show, force);
                            return true;
                        }
                    });
                    return;
                }
            }
            final Interpolator mInterpolator = new AccelerateDecelerateInterpolator();
            float distance = show ? 0 : mMarginBottom + height;
            if (show) {
                ViewPropertyAnimator.animate(this).setDuration(200).setInterpolator(mInterpolator).translationY(-distance);
            } else {
                ViewPropertyAnimator.animate(this).setDuration(200).setInterpolator(mInterpolator).translationY(distance);
            }
        }
    }

    public void attachButtonToNestedScrollView(NestedScrollView nestedScrollView) {
        ButtonNestedScrollViewScrollListener listener = new ButtonNestedScrollViewScrollListener();
        nestedScrollView.setOnScrollChangeListener(listener);
    }

    public void attachButtonToRecyclerView(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new ButtonRecyclerViewScrollListener());
    }

    private class ButtonRecyclerViewScrollListener extends RecyclerViewScrollListener {
        @Override
        public void onScrollUp() {
            buttonShowOrHide(HIDE,false);
        }

        @Override
        public void onScrollDown() {
            buttonShowOrHide(SHOW, false);
        }
    }

    private class ButtonNestedScrollViewScrollListener extends NestedScrollViewScrollListener {
        @Override
        public void onScrollUp() {
            buttonShowOrHide(HIDE,false);
        }

        @Override
        public void onScrollDown() {
            buttonShowOrHide(SHOW, false);
        }
    }
}
