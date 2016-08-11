package com.zpauly.githubapp.network.user;

import com.zpauly.githubapp.entity.response.AuthenticatedUserBean;
import com.zpauly.githubapp.entity.response.UserBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
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
    Observable<List<UserBean>> getFollowers(@Header("Authorization") String auth,
                                                 @Query("page") int pageId);

    @GET("/users/{username}/followers")
    Observable<List<UserBean>> getUserFollowers(@Header("Authorization") String auth,
                                                     @Path("username") String username,
                                                     @Query("page") int pageId);

    /**
     * List who the authenticated user is following
     * @param auth
     * @return
     */
    @GET("/user/following")
    Observable<List<UserBean>> getFollowing(@Header("Authorization") String auth,
                                                 @Query("page") int pageId);

    @GET("/users/{username}/following")
    Observable<List<UserBean>> getUserFollowing(@Header("Authorization") String auth,
                                                     @Path("username") String username,
                                                     @Query("page") int pageId);
}
