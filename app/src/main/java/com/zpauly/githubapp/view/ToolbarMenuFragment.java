package com.zpauly.githubapp.view;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.listener.OnMenuItemSelectedListener;

/**
 * Created by zpauly on 16/9/2.
 */
public abstract class ToolbarMenuFragment extends BaseFragment {
    private int menuRes = 0;

    private OnMenuItemSelectedListener mOnMenuItemSelectedListener;

    protected void setOnMenuItemSelectedListener(OnMenuItemSelectedListener listener) {
        this.mOnMenuItemSelectedListener = listener;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflateMenu();
        if (menuRes != 0) {
            inflater.inflate(menuRes, menu);
            createMenu(menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(true);
        if (mOnMenuItemSelectedListener != null) {
            mOnMenuItemSelectedListener.onItemSelected(item.getItemId());
        }
        return true;
    }

    public abstract void inflateMenu();

    public abstract void createMenu(Menu menu);

    protected void inflateMenu(int menuRes) {
        this.menuRes = menuRes;
    }
}
