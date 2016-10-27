package com.zpauly.githubapp.presenter.files;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;
import com.zpauly.githubapp.entity.response.repos.RepositoryContentBean;

import java.util.List;

/**
 * Created by zpauly on 16-8-1.
 */

public class FilesContract {
    public interface Presenter extends BasePresenter {
        void loadContent(String owner, String repo, String path);

        void loadFile(String owner, String repo, String path, String sha);
    }

    public interface View extends BaseView<Presenter> {
        void loadContentSuccess();

        void loadContentFail();

        void loadingContent(List<RepositoryContentBean> beanList);

        void loadFileSuccess();

        void loadFileFail();

        void loadingFile(String file);

        String getRef();

        String getBranch();
    }
}
