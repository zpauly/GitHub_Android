package com.zpauly.githubapp.network.user;

import com.zpauly.githubapp.entity.response.AuthenticatedUserBean;
import com.zpauly.githubapp.entity.response.FollowersBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;

/**
 * Created by zpauly on 16-6-10.
 */
public interface UserService {
    @GET("/user")
    Observable<AuthenticatedUserBean> getAuthenticatedUser(@Header("Authorization") String auth);

    //followers

    /**
     * List the authenticated user's followers
     * @param auth
     * @return
     */
    @GET("/user/followers")
    Observable<List<FollowersBean>> getFollowers(@Header("Authorization") String auth);

    /**
     * List who the authenticated user is following
     * @param auth
     * @return
     */
    @GET("/user/following")
    Observable<List<FollowersBean>> getFollowing(@Header("Authorization") String auth);
}
