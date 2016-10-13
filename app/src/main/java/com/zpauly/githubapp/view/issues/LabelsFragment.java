package com.zpauly.githubapp.view.issues;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.LabelsRecycelrViewAdapter;
import com.zpauly.githubapp.entity.response.issues.LabelBean;
import com.zpauly.githubapp.presenter.issues.LabelsContract;
import com.zpauly.githubapp.ui.FloatingActionButton;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.utils.viewmanager.RefreshViewManager;
import com.zpauly.githubapp.view.ToolbarMenuFragment;

import java.util.List;

/**
 * Created by zpauly on 2016/10/13.
 */

public class LabelsFragment extends ToolbarMenuFragment implements LabelsContract.View {
    private final String TAG = getClass().getName();

    private LabelsContract.Presenter mPresenter;

    private RefreshView mRefreshView;
    private RecyclerView mLabelsRV;
    private FloatingActionButton mEditFAB;

    private LabelsRecycelrViewAdapter mLabelsAdapter;

    private RefreshViewManager refreshViewManager;

    private String repo;
    private String owner;

    @Override
    public void inflateMenu() {

    }

    @Override
    public void createMenu(Menu menu) {

    }

    private void getParams() {
        Bundle bundle = new Bundle();
        if (bundle != null) {
            repo = bundle.getString(LabelsActivity.REPO);
            owner = bundle.getString(LabelsActivity.OWNER);
        }
    }

    @Override
    protected void initViews(View view) {
        getParams();

        mRefreshView = (RefreshView) view.findViewById(R.id.labels_RefreshView);
        mLabelsRV = (RecyclerView) view.findViewById(R.id.labels_RV);
        mEditFAB = (FloatingActionButton) view.findViewById(R.id.labels_edit_FAB);

        setupRecyclerView();

        setViewManager(new RefreshViewManager(mRefreshView, mLabelsRV, mEditFAB) {
            @Override
            public void load() {
                getLabels();
            }
        });
    }

    private void setupRecyclerView() {
        mLabelsAdapter = new LabelsRecycelrViewAdapter(getContext());
        mLabelsRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mLabelsRV.setAdapter(mLabelsAdapter);
    }

    private void getLabels() {
        mPresenter.getLabels();
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_labels, container, false);
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
