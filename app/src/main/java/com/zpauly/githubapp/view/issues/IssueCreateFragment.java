package com.zpauly.githubapp.view.issues;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.entity.request.IssueRequestBean;
import com.zpauly.githubapp.entity.response.issues.AssigneeBean;
import com.zpauly.githubapp.entity.response.issues.IssueBean;
import com.zpauly.githubapp.entity.response.issues.LabelBean;
import com.zpauly.githubapp.entity.response.issues.MilestoneBean;
import com.zpauly.githubapp.presenter.issues.IssueCreateContract;
import com.zpauly.githubapp.presenter.issues.IssueCreatePresenter;
import com.zpauly.githubapp.ui.FloatingActionButton;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.view.ToolbarMenuFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16/9/12.
 */
public class IssueCreateFragment extends ToolbarMenuFragment implements IssueCreateContract.View {
    private final String TAG = getClass().getName();

    private IssueCreateContract.Presenter mPresenter;

    private LinearLayout mChooseLayout;

    private RefreshView mRefreshView;
    private LinearLayout mLayout;

    private AppCompatButton mMilestoneBTN;
    private AppCompatButton mAssigneesBTN;
    private AppCompatButton mLabelsBTN;

    private TextInputEditText mTitleET;
    private TextInputEditText mBodyET;

    private FloatingActionButton mSendFAB;

    private String username;
    private String repoName;

    private String[] milestones;
    private String[] assignees;
    private String[] labels;

    private MaterialDialog mMilestonesDialog;
    private MaterialDialog mAssigeneesDialog;
    private MaterialDialog mLabelsDialog;

    private MaterialDialog mLoadingDialog;
    private MaterialDialog mUploadingDialog;

    private String title;
    private String body;

    private IssueRequestBean createdIssueRequestBean = new IssueRequestBean();

    private MilestoneBean selectedMilestone;
    private AssigneeBean selectedAssignee;
    private List<LabelBean> selectedLabelList = new ArrayList<>();

    private List<MilestoneBean> milestoneList = new ArrayList<>();
    private List<AssigneeBean> assigneeList = new ArrayList<>();
    private List<LabelBean> labelList = new ArrayList<>();

    @Override
    public void onStop() {
        mPresenter.stop();
        super.onStop();
    }

    @Override
    protected void initViews(View view) {
        getAttrs();

        new IssueCreatePresenter(getContext(), this);

        setDialogs();

        mRefreshView = (RefreshView) view.findViewById(R.id.issue_create_RefreshView);
        mLayout = (LinearLayout) view.findViewById(R.id.issue_create_layout);
        mChooseLayout = (LinearLayout) view.findViewById(R.id.issue_create_choose_layout);
        mMilestoneBTN = (AppCompatButton) view.findViewById(R.id.issue_create_milestone_TV);
        mAssigneesBTN = (AppCompatButton) view.findViewById(R.id.issue_create_assignees_TV);
        mLabelsBTN = (AppCompatButton) view.findViewById(R.id.issue_create_labels_TV);

        mTitleET = (TextInputEditText) view.findViewById(R.id.issue_create_title_ET);
        mBodyET = (TextInputEditText) view.findViewById(R.id.issue_create_body_ET);

        mSendFAB = (FloatingActionButton) view.findViewById(R.id.issue_create_send_FAB);

        mChooseLayout.setVisibility(View.GONE);

        setListener();

        mRefreshView.setOnRefreshStateListener(new RefreshView.OnRefreshStateListener() {
            @Override
            public void beforeRefreshing() {
                checkAssignee();
            }

            @Override
            public void onRefreshSuccess() {
                mLayout.setVisibility(View.VISIBLE);
                mSendFAB.setVisibility(View.VISIBLE);
                mRefreshView.setVisibility(View.GONE);
            }

            @Override
            public void onRefreshFail() {
                mLayout.setVisibility(View.GONE);
                mSendFAB.setVisibility(View.GONE);
                mRefreshView.setVisibility(View.VISIBLE);
            }
        });
        mRefreshView.startRefresh();
    }

    private void setDialogs() {
        mLoadingDialog = new MaterialDialog.Builder(getContext())
                .cancelable(false)
                .title(R.string.loading)
                .content(R.string.please_wait)
                .progress(true, 0)
                .build();
        mUploadingDialog = new MaterialDialog.Builder(getContext())
                .cancelable(false)
                .content(R.string.uploading)
                .progress(true, 0)
                .cancelable(false)
                .build();

    }

    private void setMilestonesDialog() {
        mMilestonesDialog = new MaterialDialog.Builder(getContext())
                .cancelable(false)
                .title(R.string.milestone)
                .items(milestones)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        if (milestones.length == 0)
                            return false;
                        selectedMilestone = milestoneList.get(which);
                        if (selectedMilestone != null && selectedMilestone.getNumber() != 0) {
                            createdIssueRequestBean.setMilestone(selectedMilestone.getNumber());
                        }
                        return false;
                    }
                })
                .positiveText(R.string.ok)
                .negativeText(R.string.cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        selectedMilestone = null;
                    }
                })
                .build();
    }

    private void setAssigneesDialog() {
        mAssigeneesDialog = new MaterialDialog.Builder(getContext())
                .cancelable(false)
                .title(R.string.assignees)
                .items(assignees)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {

                        if (assignees.length == 0)
                            return false;
                        selectedAssignee = assigneeList.get(which);
                        if (selectedAssignee != null) {
                            createdIssueRequestBean.setAssignee(selectedAssignee.getLogin());
                        }
                        return false;
                    }
                })
                .positiveText(R.string.ok)
                .negativeText(R.string.cancel)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        selectedAssignee = null;
                    }
                })
                .build();
    }

    private void setLabelsDialog() {
        mLabelsDialog = new MaterialDialog.Builder(getContext())
                .cancelable(false)
                .title(R.string.labels)
                .items(labels)
                .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                        if (labels.length == 0)
                            return false;
                        selectedLabelList.clear();
                        for (int i = 0; i < which.length; i++) {
                            selectedLabelList.add(labelList.get(i));
                        }
                        if (selectedLabelList != null || selectedLabelList.size() != 0) {
                            List<String> labels = new ArrayList<>();
                            for (LabelBean bean : selectedLabelList) {
                                labels.add(bean.getName());
                            }
                            createdIssueRequestBean.setLabels(labels);
                        }
                        return false;
                    }
                })
                .positiveText(R.string.ok)
                .negativeText(R.string.cancel)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        selectedLabelList.clear();
                    }
                })
                .build();
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
        mSendFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTitleET.getText() == null ||
                        mTitleET.getText().toString().equals("")) {
                    Snackbar.make(getView(), R.string.issue_no_title, Snackbar.LENGTH_SHORT).show();
                    return;
                } else {
                    title = mTitleET.getText().toString();
                }
                if (mBodyET.getText() != null) {
                    body = mBodyET.getText().toString();
                }
                createdIssueRequestBean.setAssignee(userInfo.getLogin());
                createdIssueRequestBean.setTitle(title);
                createdIssueRequestBean.setBody(body);
                Gson gson = new Gson();
//                Log.i(TAG, "title : " + createdIssueRequestBean.getTitle());
//                Log.i(TAG, "body : " + createdIssueRequestBean.getBody());
//                Log.i(TAG, "assignees : " + jsonArray.toString());
//                Log.i(TAG, "assignees : " + createdIssueRequestBean.getAssignees());
//                Log.i(TAG, "milestone : " + createdIssueRequestBean.getMilestone());
//                Log.i(TAG, "labels : " + createdIssueRequestBean.getLabels());
                Log.i(TAG, "issue : " + gson.toJson(createdIssueRequestBean));
//                mUploadingDialog.show();
//                mPresenter.createAnIssue();
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
        if (assignees == null) {
            mPresenter.getAssignees();
            mLoadingDialog.show();
        } else {
            mAssigeneesDialog.show();
        }
    }

    private void getMilestones() {
        if (milestones == null) {
            mPresenter.getMilestones();
            mLoadingDialog.show();
        } else {
            mMilestonesDialog.show();
        }
    }

    private void getLabels() {
        if (labels == null) {
            mPresenter.getLabels();
            mLoadingDialog.show();
        } else {
            mLabelsDialog.show();
        }
    }

    @Override
    public void checkSuccess() {
        if (!mRefreshView.isRefreshSuccess())
            mRefreshView.refreshSuccess();
        mChooseLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void checkFail() {
        mRefreshView.refreshFail();
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
    public void gettingAssignees(final List<AssigneeBean> assigneeBeen) {
        assigneeList.addAll(assigneeBeen);
        if (assignees == null || assignees.length == 0) {
            String[] items = new String[assigneeBeen.size()];
            for (int i = 0; i < assigneeBeen.size(); i++) {
                items[i] = assigneeBeen.get(i).getLogin();
            }
            assignees = items;
        }
        setAssigneesDialog();
        mAssigeneesDialog.show();
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
    public void gettingMilestones(final List<MilestoneBean> milestoneBeen) {
        milestoneList.addAll(milestoneBeen);
        if (milestones == null || milestones.length == 0) {
            String[] items = new String[milestoneBeen.size()];
            for (int i = 0; i < milestoneBeen.size(); i++) {
                items[i] = milestoneBeen.get(i).getTitle();
            }
            milestones = items;
        }
        setMilestonesDialog();
        mMilestonesDialog.show();
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
    public void gettingLabels(final List<LabelBean> labelBeen) {
        labelList.addAll(labelBeen);
        if (labels == null || labels.length == 0) {
            String[] items = new String[labelBeen.size()];
            for (int i = 0; i < labelBeen.size(); i++) {
                items[i] = labelBeen.get(i).getName();
            }
            labels = items;
        }
        setLabelsDialog();
        mLabelsDialog.show();
    }

    @Override
    public void createIssueSuccess() {
        mUploadingDialog.dismiss();
        Snackbar.make(getView(), R.string.upload_success, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void createIssueFail() {
        mUploadingDialog.dismiss();
        Snackbar.make(getView(), R.string.upload_fail, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void creatingIssue(IssueBean issueBean) {

    }

    @Override
    public IssueRequestBean getIssueBean() {
        return createdIssueRequestBean;
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

    @Override
    public void inflateMenu() {
    }

    @Override
    public void createMenu(Menu menu) {

    }
}
