package com.zpauly.githubapp.network.user;

import com.zpauly.githubapp.entity.response.AuthenticatedUserBean;
import com.zpauly.githubapp.entity.response.FollowersBean;
import com.zpauly.githubapp.entity.response.UserBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by zpauly on 16-6-10.
 */
public interface UserService {
    @GET("/user")
    Observable<AuthenticatedUserBean> getAuthenticatedUser(@Header("Authorization") String auth);

    /**
     * Get a single user
     * @param username
     * @return
     */
    @GET("/users/{username}")
    Observable<UserBean> getUser(@Path("username") String username);

    //followers

    /**
     * List the authenticated user's followers
     * @param auth
     * @return
     */
    @GET("/user/followers")
    Observable<List<FollowersBean>> getFollowers(@Header("Authorization") String auth);

    @GET("/users/{username}/followers")
    Observable<List<FollowersBean>> getUserFollowers(@Path("username") String username);

    /**
     * List who the authenticated user is following
     * @param auth
     * @return
     */
    @GET("/user/following")
    Observable<List<FollowersBean>> getFollowing(@Header("Authorization") String auth);

    @GET("/users/{username}/following")
    Observable<List<FollowersBean>> getUserFollowing(@Path("username") String username);
}
