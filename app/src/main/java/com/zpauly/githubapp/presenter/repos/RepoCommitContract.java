package com.zpauly.githubapp.presenter.repos;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;
import com.zpauly.githubapp.entity.response.repos.SingleCommitBean;

import java.util.List;

/**
 * Created by zpauly on 16/9/20.
 */

public class RepoCommitContract {
    public interface Presenter extends BasePresenter {
        void getCommits();

        void setPageId(int pageId);
    }

    public interface View extends BaseView<Presenter> {
        void getCommitsSuccess();

        void getCommitsFail();

        void gettingCommits(List<SingleCommitBean> singleCommitBeen);

        String getOwner();

        String getRepo();
    }
}
