package com.zpauly.githubapp.presenter.issues;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;
import com.zpauly.githubapp.entity.response.CommentBean;

import java.util.List;

/**
 * Created by zpauly on 16/9/6.
 */
public class IssueContentContract {
    public interface Presenter extends BasePresenter {
        void getIssueComments();

        void setPageId(int pageId);
    }

    public interface View extends BaseView<Presenter> {
        void getCommentsSuccess();

        void getCommentsFail();

        void gettingComments(List<CommentBean> commentBeen);

        String getRepo();

        String getOwner();

        int getNum();
    }
}
