package com.zpauly.githubapp.db;

import com.zpauly.githubapp.entity.response.repos.RepositoryContentBean;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by zpauly on 16-8-1.
 */

public class FileDirDao {
    public static boolean insert(RepositoryContentBean bean, String parentPath) {
        FileDirModel model = new FileDirModel();
        model.setParentPath("root system/" + parentPath);
        model.setPath(bean.getPath());
        model.setType(bean.getType());
        model.setSha(bean.getSha());
        return model.save();
    }

    public static List<FileDirModel> query(String tip, String data) {
        return DataSupport.where(tip + " = ?", data).find(FileDirModel.class);
    }

    public static int delete() {
        return DataSupport.deleteAll(FileDirModel.class);
    }
}
