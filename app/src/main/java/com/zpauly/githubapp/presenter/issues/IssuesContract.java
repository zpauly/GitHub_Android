package com.zpauly.githubapp.presenter.issues;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;
import com.zpauly.githubapp.entity.response.issues.IssueBean;

import java.util.List;

/**
 * Created by zpauly on 16/9/4.
 */
public class IssuesContract {
    public interface Presenter extends BasePresenter {
        int getPageId();

        void setPageId(int pageId);

        void getIssues();
    }

    public interface View extends BaseView<Presenter> {
        String getState();

        String getFilter();

        String getSort();

        String getDirection();

        void getIssueSuccess();

        void getIssueFail();

        void gettingIssues(List<IssueBean> list);
    }
}
