package com.zpauly.githubapp.presenter.issues;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;
import com.zpauly.githubapp.entity.request.IssueRequestBean;
import com.zpauly.githubapp.entity.response.issues.AssigneeBean;
import com.zpauly.githubapp.entity.response.issues.IssueBean;
import com.zpauly.githubapp.entity.response.issues.LabelBean;
import com.zpauly.githubapp.entity.response.issues.MilestoneBean;

import java.util.List;

/**
 * Created by zpauly on 16/9/12.
 */
public class IssueCreateContract {
    public interface Presenter extends BasePresenter {
        void checkAssignee();

        void getAssignees();

        void getMilestones();

        void getLabels();

        void createAnIssue();
    }

    public interface View extends BaseView<Presenter> {
        void checkSuccess();

        void checkFail();

        void checkNotFound();

        void getAssigneesSuccess();

        void getAssigneeFail();

        void gettingAssignees(List<AssigneeBean> assigneeBeen);

        void getMilestonesSuccess();

        void getMilestonesFail();

        void gettingMilestones(List<MilestoneBean> milestoneBeen);

        void getLabelsSuccess();

        void getLabelsFail();

        void gettingLabels(List<LabelBean> labelBeen);

        void createIssueSuccess();

        void createIssueFail();

        void creatingIssue(IssueBean issueBean);

        IssueRequestBean getIssueBean();

        String getOwner();

        String getUsername();

        String getRepoName();
    }
}
