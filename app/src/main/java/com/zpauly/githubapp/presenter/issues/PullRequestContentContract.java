package com.zpauly.githubapp.presenter.issues;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;
import com.zpauly.githubapp.entity.response.CommentBean;
import com.zpauly.githubapp.entity.response.repos.FileBean;
import com.zpauly.githubapp.entity.response.repos.SingleCommitBean;

import java.util.List;

/**
 * Created by zpauly on 2016/10/22.
 */

public class PullRequestContentContract {
    public interface Presenter extends BasePresenter {
        void getComments();

        void getCommits();

        void getFiles();

        void setPageId(int pageId);
    }

    public interface View extends BaseView<Presenter> {
        void getCommentsSuccess();

        void getCommentsFail();

        void gettingComments(List<CommentBean> commentBeen);

        void getCommitsSuccess();

        void getCommitsFail();

        void gettingCommits(List<SingleCommitBean> commitBeen);

        void getFilesSuccess();

        void getFilsFail();

        void gettingFiles(List<FileBean> fileBeen);

        String getOwner();

        String getRepo();

        int getNumber();
    }
}
