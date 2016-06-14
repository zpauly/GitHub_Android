package com.zpauly.githubapp.db;

import android.content.ContentValues;

import com.zpauly.githubapp.entity.response.AuthenticatedUser;

import org.litepal.crud.DataSupport;

/**
 * Created by zpauly on 16-6-12.
 */
public class UserDao {
    public static boolean insertUser(AuthenticatedUser user) {
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
        return userModel.save();
    }

    public static int deleteUser() {
        return DataSupport.deleteAll(UserModel.class);
    }

    public static int updateUser(ContentValues values, String conditions) {
        return DataSupport.updateAll(UserModel.class, values, conditions);
    }
}
