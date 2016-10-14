package com.zpauly.githubapp.view.issues;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
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
import com.zpauly.githubapp.view.ToolbarActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16/9/12.
 */
public class IssueCreateActivity extends ToolbarActivity implements IssueCreateContract.View {
    private final String TAG = getClass().getName();

    public static final String USERNAME = "USERNAME";
    public static final String REPONAME = "REPONAME";

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
    protected void onStop() {
        mPresenter.stop();
        super.onStop();
    }

    @Override
    public void initViews() {
        getParams();

        setContent(R.layout.content_create_issue);

        new IssueCreatePresenter(this, this);

        setDialogs();

        mRefreshView = (RefreshView) findViewById(R.id.issue_create_RefreshView);
        mLayout = (LinearLayout) findViewById(R.id.issue_create_layout);
        mChooseLayout = (LinearLayout) findViewById(R.id.issue_create_choose_layout);
        mMilestoneBTN = (AppCompatButton) findViewById(R.id.issue_create_milestone_TV);
        mAssigneesBTN = (AppCompatButton) findViewById(R.id.issue_create_assignees_TV);
        mLabelsBTN = (AppCompatButton) findViewById(R.id.issue_create_labels_TV);

        mTitleET = (TextInputEditText) findViewById(R.id.issue_create_title_ET);
        mBodyET = (TextInputEditText) findViewById(R.id.issue_create_body_ET);

        mSendFAB = (FloatingActionButton) findViewById(R.id.issue_create_send_FAB);

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

    private void getParams() {
        username = getIntent().getStringExtra(USERNAME);
        repoName = getIntent().getStringExtra(REPONAME);
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(R.string.create_issue);
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public static void launchActivity(Context context, String username, String repoName) {
        Intent intent = new Intent();
        intent.putExtra(USERNAME, username);
        intent.putExtra(REPONAME, repoName);
        intent.setClass(context, IssueCreateActivity.class);
        context.startActivity(intent);
//        ((Activity) context).finish();
    }

    private void setDialogs() {
        mLoadingDialog = new MaterialDialog.Builder(this)
                .cancelable(false)
                .title(R.string.loading)
                .content(R.string.please_wait)
                .progress(true, 0)
                .build();
        mUploadingDialog = new MaterialDialog.Builder(this)
                .cancelable(false)
                .content(R.string.uploading)
                .progress(true, 0)
                .cancelable(false)
                .build();

    }

    private void setMilestonesDialog() {
        mMilestonesDialog = new MaterialDialog.Builder(this)
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
        mAssigeneesDialog = new MaterialDialog.Builder(this)
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
        mLabelsDialog = new MaterialDialog.Builder(this)
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
                    Snackbar.make(getCurrentFocus(), R.string.issue_no_title, Snackbar.LENGTH_SHORT).show();
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
                mUploadingDialog.show();
                mPresenter.createAnIssue();
            }
        });
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
    public void checkNotFound() {
        if (!mRefreshView.isRefreshSuccess())
            mRefreshView.refreshSuccess();
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
        Snackbar.make(getCurrentFocus(), R.string.upload_success, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void createIssueFail() {
        mUploadingDialog.dismiss();
        Snackbar.make(getCurrentFocus(), R.string.upload_fail, Snackbar.LENGTH_SHORT).show();
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
}
