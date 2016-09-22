package com.zpauly.githubapp.presenter.comment;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;
import com.zpauly.githubapp.entity.response.CommentBean;

import java.util.List;

/**
 * Created by zpauly on 16/9/22.
 */

public class CommitCommentContract {
    public interface Presenter extends BasePresenter {
        void getASingleCommitComments();

        void setPageId(int pageId);
    }

    public interface View extends BaseView<Presenter> {
        void getCommentsSuccess();

        void getCommentsFail();

        void gettingComments(List<CommentBean> commentBeen);

        String getOwner();

        String getRepo();

        String getRef();
    }
}
