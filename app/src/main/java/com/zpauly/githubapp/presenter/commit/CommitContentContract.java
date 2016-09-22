package com.zpauly.githubapp.presenter.commit;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;
import com.zpauly.githubapp.entity.response.repos.SingleCommitBean;

/**
 * Created by zpauly on 16/9/22.
 */

public class CommitContentContract {
    public interface Presenter extends BasePresenter {
        void getSingleCommit();
    }

    public interface View extends BaseView<Presenter> {
        void getCommitSuccess();

        void getCommitFail();

        void gettingCommit(SingleCommitBean singleCommitBean);

        String getRepo();

        String getOwner();

        String getSha();
    }
}
