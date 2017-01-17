package com.zpauly.githubapp.view.issues;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.LabelsRecycelrViewAdapter;
import com.zpauly.githubapp.entity.response.issues.LabelBean;
import com.zpauly.githubapp.presenter.issues.LabelsContract;
import com.zpauly.githubapp.presenter.issues.LabelsPresenter;
import com.zpauly.githubapp.ui.FloatingActionButton;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.utils.viewmanager.RefreshViewManager;
import com.zpauly.githubapp.view.ToolbarActivity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zpauly on 2016/10/13.
 */

public class LabelsActivity extends ToolbarActivity implements LabelsContract.View {
    private final String TAG = getClass().getName();

    public static final String REPO = "REPO";
    public static final String OWNER = "OWNER";

    private LabelsContract.Presenter mPresenter;

    @BindView(R.id.labels_RefreshView) public RefreshView mRefreshView;
    @BindView(R.id.labels_RV) public RecyclerView mLabelsRV;
    @BindView(R.id.labels_edit_FAB) public FloatingActionButton mEditFAB;

    private LabelsRecycelrViewAdapter mLabelsAdapter;

    private RefreshViewManager refreshViewManager;

    private String repo;
    private String owner;

    @Override
    protected void onStop() {
        mPresenter.stop();
        super.onStop();
    }

    @Override
    public void initViews() {
        new LabelsPresenter(this, this);

        setupRecyclerView();

        setViewManager(new RefreshViewManager(mRefreshView, mLabelsRV) {
            @Override
            public void load() {
                getLabels();
            }
        });
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(R.string.labels);
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void getParams() {
        repo = getIntent().getStringExtra(REPO);
        owner = getIntent().getStringExtra(OWNER);
    }

    @Override
    public void initContent() {
        super.initContent();
        setContent(R.layout.content_labels);
    }

    public static void launchActivity(Context context, String repo, String owner) {
        Intent intent = new Intent();
        intent.putExtra(REPO, repo);
        intent.putExtra(OWNER, owner);
        intent.setClass(context, LabelsActivity.class);
        context.startActivity(intent);
    }

    private void setupRecyclerView() {
        mLabelsAdapter = new LabelsRecycelrViewAdapter(this);
        mLabelsRV.setLayoutManager(new LinearLayoutManager(this));
        mLabelsRV.setAdapter(mLabelsAdapter);
    }

    private void getLabels() {
        mPresenter.getLabels();
    }

    @Override
    public void getLabelsSuccess() {
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshSuccess();
        }
    }

    @Override
    public void getLabelsFail() {
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshFail();
        }
    }

    @Override
    public void gettingLabels(List<LabelBean> labelBeen) {
        mLabelsAdapter.swapAllData(labelBeen);
    }

    @Override
    public String getRepo() {
        return repo;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public void setPresenter(LabelsContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
