package com.zpauly.githubapp.db;

import com.zpauly.githubapp.entity.response.repos.RepositoryContentBean;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zpauly on 16-8-1.
 */

public class FileDirDao {
    public static boolean insert(final RepositoryContentBean bean, final String parentPath) {
        final boolean[] result = {false};
        Observable.just(bean)
                .map(new Func1<RepositoryContentBean, Boolean>() {

                    @Override
                    public Boolean call(RepositoryContentBean repositoryContentBean) {
                        FileDirModel model = new FileDirModel();
                        model.setParentPath("root system/" + parentPath);
                        model.setPath(bean.getPath());
                        model.setType(bean.getType());
                        model.setSha(bean.getSha());
                        return model.save();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        result[0] = aBoolean;
                    }
                });
        
        return result[0];
    }

    public static List<FileDirModel> query(final String tip, final String data) {
        final List<FileDirModel> list = new ArrayList<>();
        Observable.just(tip)
                .map(new Func1<String, List<FileDirModel>>() {
                    @Override
                    public List<FileDirModel> call(String s) {
                        return DataSupport.where(s + " = ?", data).find(FileDirModel.class);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<FileDirModel>>() {
                    @Override
                    public void call(List<FileDirModel> fileDirModels) {
                        list.addAll(fileDirModels);
                    }
                });
        return list;
    }

    public static void delete() {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                DataSupport.deleteAll(FileDirModel.class);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });
    }
}
