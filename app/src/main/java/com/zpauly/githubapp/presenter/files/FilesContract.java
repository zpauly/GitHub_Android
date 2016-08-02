package com.zpauly.githubapp.presenter.files;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;
import com.zpauly.githubapp.db.FileDirModel;
import com.zpauly.githubapp.entity.response.RepositoryContentBean;

import java.util.List;

/**
 * Created by zpauly on 16-8-1.
 */

public class FilesContract {
    public interface Presenter extends BasePresenter {
        void loadContent(String owner, String repo, String path);

        void getContentFromCache(String path);

        void loadFile(String owner, String repo, String path);
    }

    public interface View extends BaseView<Presenter> {
        void loadContentSuccess();

        void loadContentFail();

        void loadingContent(List<RepositoryContentBean> beanList);

        void getContentSuccess();

        void getContentFail();

        void gettingContent(List<FileDirModel> list);

        void loadFileSuccess();

        void loadFileFail();

        void loadingFile(String file);
    }
}
