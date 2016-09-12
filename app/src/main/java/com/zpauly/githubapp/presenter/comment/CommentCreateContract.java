package com.zpauly.githubapp.presenter.comment;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;
import com.zpauly.githubapp.entity.response.issues.IssueCommentBean;

/**
 * Created by zpauly on 16/9/8.
 */
public class CommentCreateContract {
    public interface Presenter extends BasePresenter {
        void createAComment();
    }

    public interface View extends BaseView<Presenter> {
        void createCommentSuccess();

        void createCommentFail();

        void creatingComment(IssueCommentBean issueCommentBean);

        String getOwner();

        String getRepo();

        int getNum();

        String getCommentBody();
    }
}
