package com.zpauly.githubapp.presenter.issues;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;
import com.zpauly.githubapp.entity.response.issues.AssigneeBean;

import java.util.List;

/**
 * Created by zpauly on 16/9/12.
 */
public class IssueCreateContract {
    public interface Presenter extends BasePresenter {
        void checkAssignee();

        void getAssignees();
    }

    public interface View extends BaseView<Presenter> {
        void checkSuccess();

        void checkFail();

        void getAssigneesSuccess();

        void getAssigneeFail();

        void gettingAssignees(List<AssigneeBean> assigneeBeen);

        String getOwner();

        String getUsername();

        String getRepoName();
    }
}
