package com.zpauly.githubapp.presenter.issues;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;
import com.zpauly.githubapp.entity.response.issues.LabelBean;

import java.util.List;

/**
 * Created by zpauly on 2016/10/13.
 */

public class LabelsContract {
    public interface Presenter extends BasePresenter {
        void getLabels();
    }

    public interface View extends BaseView<Presenter> {
        void getLabelsSuccess();

        void getLabelsFail();

        void gettingLabels(List<LabelBean> labelBeen);

        String getRepo();

        String getOwner();
    }
}
