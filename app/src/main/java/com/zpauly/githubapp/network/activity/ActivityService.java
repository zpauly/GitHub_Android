package com.zpauly.githubapp.network.activity;

import android.support.annotation.Nullable;

import com.zpauly.githubapp.entity.response.RepositoriesBean;
import com.zpauly.githubapp.entity.response.events.EventsBean;

import java.util.List;

import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zpauly on 16-7-17.
 */

public interface ActivityService {
    String SORT_CREATED = "created";//Default

    String SORT_UPDATED = "updated";

    String DIRECTION_ASC = "asc";//Default

    String DIRECTION_DESC = "desc";

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

    /**
     * Check if you are starring a repository
     * @param auth
     * @param owner
     * @param repo
     * @return
     */
    @GET("/user/starred/{owner}/{repo}")
    Observable<String> isRepoStarred(@Header("Authorization") String auth,
                                     @Path("owner") String owner,
                                     @Path("repo") String repo);

    /**
     * Star a repository
     * @param auth
     * @param owner
     * @param repo
     * @return
     */
    @PUT("/user/starred/{owner}/{repo}")
    Observable<String> starARepo(@Header("Authorization") String auth,
                                     @Path("owner") String owner,
                                     @Path("repo") String repo);

    /**
     * Unstar a repository
     * @param auth
     * @param owner
     * @param repo
     * @return
     */
    @DELETE("/user/starred/{owner}/{repo}")
    Observable<String> unstarARepo(@Header("Authorization") String auth,
                                   @Path("owner") String owner,
                                   @Path("repo") String repo);

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
