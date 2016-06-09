package com.zpauly.githubapp.base;

/**
 * Created by zpauly on 16-6-9.
 */
public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
}
