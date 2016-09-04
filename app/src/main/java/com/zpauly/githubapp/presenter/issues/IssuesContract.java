package com.zpauly.githubapp.presenter.issues;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;

/**
 * Created by zpauly on 16/9/4.
 */
public class IssuesContract {
    public interface Presenter extends BasePresenter {
        void getIssues();
    }

    public interface View extends BaseView<Presenter> {
        String getState();

        String getFilter();

        String getSort();

        String getDirection();

        void getIssueSuccess();

        void getIssueFail();
    }
}
