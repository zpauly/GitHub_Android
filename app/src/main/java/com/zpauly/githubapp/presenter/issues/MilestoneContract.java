package com.zpauly.githubapp.presenter.issues;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;
import com.zpauly.githubapp.entity.response.issues.MilestoneBean;

import java.util.List;

/**
 * Created by zpauly on 2016/10/11.
 */

public class MilestoneContract {
    public interface Presenter extends BasePresenter {
        void setPageId(int pageId);

        void getMilestones();
    }

    public interface View extends BaseView<Presenter> {
        void getMilestonesSuccess();

        void getMilestoneFail();

        void gettingMilestones(List<MilestoneBean> milestoneBeen);

        String getRepo();

        String getOwner();

        String getState();

        String getSort();

        String getDirection();
    }
}
