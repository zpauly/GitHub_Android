package com.zpauly.githubapp.view.gists;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zpauly.githubapp.entity.response.gists.GistFileBean;
import com.zpauly.githubapp.entity.response.gists.GistsBean;
import com.zpauly.githubapp.view.ToolbarActivity;

import java.util.ArrayList;

/**
 * Created by zpauly on 16-8-6.
 */

public class GistContentActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    public static final String GIST_BUNDLE = "GIST_BUNDLE";
    public static final String FILE_LIST_BUNDLE = "FILE_LIST_BUNDLE";

    private GistsBean bean;
    private ArrayList<GistFileBean> list;

    @Override
    public void initViews() {
        getAttrs();
    }

    private void getAttrs() {
        bean = getIntent().getParcelableExtra(GIST_BUNDLE);
        list = getIntent().getParcelableArrayListExtra(FILE_LIST_BUNDLE);
        Log.i(TAG, String.valueOf(bean.getFiles().size()));

        setFragment();
    }

    private void setFragment() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(GIST_BUNDLE, bean);
        bundle.putParcelableArrayList(FILE_LIST_BUNDLE, list);
        GistContentFragment fragment = new GistContentFragment();
        fragment.setArguments(bundle);
        setContent(fragment);
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(bean.getId());
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public static void launchActivity(Context context, GistsBean bean, ArrayList<GistFileBean> list) {
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(FILE_LIST_BUNDLE, list);
        intent.putExtra(GIST_BUNDLE, bean);
        intent.setClass(context, GistContentActivity.class);
        context.startActivity(intent);
<<<<<<< HEAD
<<<<<<< HEAD
//        ((Activity) context).finish();
=======
>>>>>>> parent of 502b4dc... complete gist file
=======
        ((Activity) context).finish();
>>>>>>> parent of 9b3c48f... Updated ReadMe.md
    }
}
