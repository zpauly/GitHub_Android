package com.zpauly.githubapp.network.activity;

import android.support.annotation.Nullable;

import com.zpauly.githubapp.entity.response.RepositoriesBean;
import com.zpauly.githubapp.entity.response.StarredRepositories;
import com.zpauly.githubapp.entity.response.UserBean;
import com.zpauly.githubapp.entity.response.events.EventsBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zpauly on 16-7-17.
 */

public interface ActivityService {
    //starring
    /**
     * List repositories being starred
     *
     * List repositories being starred by the authenticated user.
     *
     * @param auth
     * @param sort One of created (when the repository was starred)
     *             or updated (when it was last pushed to).
     *             Default: created
     * @param direction One of asc (ascending) or desc (descending).
     *                  Default: desc
     * @return
     */
    @GET("/user/starred")
    Observable<List<RepositoriesBean>> getStarredRepositories(@Header("Authorization") String auth
            , @Nullable @Query("sort") String sort, @Nullable @Query("direction") String direction,
                                                                 @Query("page") int pageId);

    @GET("/users/{username}/starred")
    Observable<List<RepositoriesBean>> getOthersRepositories(@Path("username") String username, @Query("page") int pageId);

    //----------------------------------------
    //Events

    /**
     * These are events that you've received by watching repos and following users.
     * If you are authenticated as the given user, you will see private events.
     * Otherwise, you'll only see public events.
     * @param auth
     * @param username
     * @return
     */
    @GET("/users/{username}/received_events")
    Observable<List<EventsBean>> getReceivedEvents(@Header("Authorization") String auth,
                                                   @Path("username") String username,
                                                   @Query("page") int pageId);

    /**
     * List events performed by a user
     * If you are authenticated as the given user,
     * you will see your private events. Otherwise,
     * you'll only see public events.
     * @param auth
     * @param username
     * @return
     */
    @GET("/users/{username}/events")
    Observable<List<EventsBean>> getUserEvents(@Header("Authorization") String auth,
                                               @Path("username") String username,
                                               @Query("page") int pageId);
}
