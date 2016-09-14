package com.zpauly.githubapp.view.issues;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.entity.response.issues.AssigneeBean;
import com.zpauly.githubapp.entity.response.issues.LabelBean;
import com.zpauly.githubapp.entity.response.issues.MilestoneBean;
import com.zpauly.githubapp.presenter.issues.IssueCreateContract;
import com.zpauly.githubapp.presenter.issues.IssueCreatePresenter;
import com.zpauly.githubapp.ui.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16/9/12.
 */
public class IssueCreateFragment extends BaseFragment implements IssueCreateContract.View {
    private final String TAG = getClass().getName();

    private IssueCreateContract.Presenter mPresenter;

    private LinearLayout mChooseLayout;

    private AppCompatButton mMilestoneBTN;
    private AppCompatButton mAssigneesBTN;
    private AppCompatButton mLabelsBTN;

    private TextInputEditText mTitleET;
    private TextInputEditText mBodyET;

    private FloatingActionButton mSendFAB;

    private String username;
    private String repoName;

    private List<String> milestones;
    private List<String> assignees;
    private List<String> labels;

    private MaterialDialog mMilestonesDialog;
    private MaterialDialog mAssigeneesDialog;
    private MaterialDialog mLabelsDialog;

    private MaterialDialog mLoadingDialog;

    private List<String> milesstoneList = new ArrayList<>();
    private List<String> assigneeList = new ArrayList<>();
    private List<String> labelList = new ArrayList<>();


    @Override
    protected void initViews(View view) {
        getAttrs();

        new IssueCreatePresenter(getContext(), this);

        mLoadingDialog = new MaterialDialog.Builder(getContext())
                .title(R.string.loading)
                .content(R.string.please_wait)
                .progress(true, 0)
                .build();

        mChooseLayout = (LinearLayout) view.findViewById(R.id.issue_create_choose_layout);
        mMilestoneBTN = (AppCompatButton) view.findViewById(R.id.issue_create_milestone_TV);
        mAssigneesBTN = (AppCompatButton) view.findViewById(R.id.issue_create_assignees_TV);
        mLabelsBTN = (AppCompatButton) view.findViewById(R.id.issue_create_labels_TV);

        mTitleET = (TextInputEditText) view.findViewById(R.id.issue_create_title_ET);
        mBodyET = (TextInputEditText) view.findViewById(R.id.issue_create_body_ET);

        mSendFAB = (FloatingActionButton) view.findViewById(R.id.issue_create_send_FAB);

        mChooseLayout.setVisibility(View.GONE);

        checkAssignee();

        setListener();
    }

    private void setListener() {
        mMilestoneBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mChooseLayout.isEnabled()) {
                    getMilestones();
                }
            }
        });

        mAssigneesBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mChooseLayout.isEnabled()) {
                    getAssignees();
                }
            }
        });

        mLabelsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mChooseLayout.isEnabled()) {
                    getLabels();
                }
            }
        });
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_create_issue, container, false);
    }

    private void getAttrs() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            username = bundle.getString(IssueCreateActivity.USERNAME);
            repoName = bundle.getString(IssueCreateActivity.REPONAME);
        }
    }

    private void checkAssignee() {
        mPresenter.checkAssignee();
    }

    private void getAssignees() {
        mPresenter.getAssignees();
        mLoadingDialog.show();
    }

    private void getMilestones() {
        mPresenter.getMilestones();
        mLoadingDialog.show();
    }

    private void getLabels() {
        mPresenter.getLabels();
        mLoadingDialog.show();
    }

    @Override
    public void checkSuccess() {
        mChooseLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void checkFail() {
        mChooseLayout.setVisibility(View.GONE);
    }

    @Override
    public void getAssigneesSuccess() {
        mLoadingDialog.dismiss();
        mAssigeneesDialog.show();
    }

    @Override
    public void getAssigneeFail() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void gettingAssignees(List<AssigneeBean> assigneeBeen) {
        String[] items = new String[assigneeBeen.size()];
        for (int i = 0; i < assigneeBeen.size(); i++) {
            items[i] = assigneeBeen.get(i).getLogin();
        }
        mAssigeneesDialog = new MaterialDialog.Builder(getContext())
                .title(R.string.assignees)
                .items(items)
                .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                        return false;
                    }
                })
                .positiveText(R.string.ok)
                .negativeText(R.string.cancel)
                .build();
    }

    @Override
    public void getMilestonesSuccess() {
        mLoadingDialog.dismiss();
        mMilestonesDialog.show();
    }

    @Override
    public void getMilestonesFail() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void gettingMilestones(List<MilestoneBean> milestoneBeen) {
        final String[] items = new String[milestoneBeen.size()];
        for (int i = 0; i < milestoneBeen.size(); i++) {
            items[i] = milestoneBeen.get(i).getTitle();
        }
        mMilestonesDialog = new MaterialDialog.Builder(getContext())
                .title(R.string.milestone)
                .items(items)
                .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                        /*for (int i = 0; i < text.length; i++) {
                            milesstoneList.add(text[i].toString());
                        }*/
                        Toast.makeText(getContext(), text[0].toString(), Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .positiveText(R.string.ok)
                .negativeText(R.string.cancel)
                .build();
    }

    @Override
    public void getLabelsSuccess() {
        mLoadingDialog.dismiss();
        mLabelsDialog.show();
    }

    @Override
    public void getLabelsFail() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void gettingLabels(List<LabelBean> labelBeen) {
        String[] items = new String[labelBeen.size()];
        for (int i = 0; i < labelBeen.size(); i++) {
            items[i] = labelBeen.get(i).getName();
        }
        mLabelsDialog = new MaterialDialog.Builder(getContext())
                .title(R.string.labels)
                .items(items)
                .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                        return false;
                    }
                })
                .positiveText(R.string.ok)
                .negativeText(R.string.cancel)
                .build();
    }

    @Override
    public String getOwner() {
        return username;
    }

    @Override
    public String getUsername() {
        return userInfo.getLogin();
    }

    @Override
    public String getRepoName() {
        return repoName;
    }

    @Override
    public void setPresenter(IssueCreateContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
