package com.zpauly.githubapp.network.activity;

import android.support.annotation.Nullable;

import com.zpauly.githubapp.entity.response.StarredRepositories;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zpauly on 16-7-17.
 */

public interface ActivityService {
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
    Observable<List<StarredRepositories>> getStarredRepositories(@Header("Authorization") String auth
            , @Nullable @Query("sort") String sort, @Nullable @Query("direction") String direction);
}
