package com.zpauly.githubapp.db;

import android.content.ContentValues;

import com.zpauly.githubapp.entity.response.AuthenticatedUserBean;
import com.zpauly.githubapp.entity.response.FollowersBean;
import com.zpauly.githubapp.entity.response.UserBean;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by zpauly on 16-6-12.
 */
public class UserDao {
    public static boolean insertUser(AuthenticatedUserBean user) {
        UserModel userModel = new UserModel();
        userModel.setLogin(user.getLogin());
        userModel.setName(user.getName());
        userModel.setLocation(user.getLocation());
        userModel.setAvatar_url(user.getAvatar_url());
        userModel.setBio(user.getBio());
        userModel.setBlog(user.getBlog());
        userModel.setEmail(user.getEmail());
        userModel.setCompany(user.getCompany());
        userModel.setHirable(user.isHireable());
        userModel.setFollowing(user.getFollowing());
        userModel.setFollowers(user.getFollowers());
        userModel.setCreated_at(user.getCreated_at());
        userModel.setUpdated_at(user.getUpdated_at());
        userModel.setPublic_gists(user.getPublic_gists());
        userModel.setPublic_repos(user.getPublic_repos());
        userModel.setType(user.getType());
        userModel.setUser(true);
        return userModel.save();
    }

    public static boolean insert(UserBean bean) {
        UserModel userModel = new UserModel();
        userModel.setLogin(bean.getLogin());
        userModel.setName(bean.getName());
        userModel.setLocation(bean.getLocation());
        userModel.setAvatar_url(bean.getAvatar_url());
        userModel.setBio(bean.getBio());
        userModel.setBlog(bean.getBlog());
        userModel.setEmail(bean.getEmail());
        userModel.setCompany(bean.getCompany());
        userModel.setHirable(bean.isHireable());
        userModel.setFollowing(bean.getFollowing());
        userModel.setFollowers(bean.getFollowers());
        userModel.setCreated_at(bean.getCreated_at());
        userModel.setUpdated_at(bean.getUpdated_at());
        userModel.setPublic_gists(bean.getPublic_gists());
        userModel.setPublic_repos(bean.getPublic_repos());
        userModel.setType(bean.getType());
        userModel.setUser(false);
        return userModel.save();
    }

    public static boolean insertUser(FollowersBean other) {
        UserModel userModel = new UserModel();
        userModel.setLogin(other.getLogin());
        userModel.setUser(true);
        return userModel.save();
    }

    public static List<UserModel> query(String tip, String data) {
        return DataSupport.where(tip + " = ?", data).find(UserModel.class);
    }

    public static UserModel queryUser() {
        List<UserModel> list = DataSupport.findAll(UserModel.class);
        if (list.size() == 0)
            return null;
        return list.get(list.size() - 1);
    }

    public static int deleteUser() {
        return DataSupport.deleteAll(UserModel.class);
    }

    public static int updateUser(ContentValues values, String conditions) {
        return DataSupport.updateAll(UserModel.class, values, conditions);
    }
}
