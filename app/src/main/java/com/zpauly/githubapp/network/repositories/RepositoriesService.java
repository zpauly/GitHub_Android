package com.zpauly.githubapp.network.repositories;

import android.support.annotation.Nullable;

import com.zpauly.githubapp.entity.response.RepositoriesBean;

import java.util.List;


import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zpauly on 16-7-13.
 */

public interface RepositoriesService {
    /**
     * List repositories that are accessible to the authenticated user.
     * This includes repositories owned by the authenticated user,
     * repositories where the authenticated user is a collaborator,
     * and repositories that the authenticated user has access to through an organization membership.
     * @param auth
     * @param affiliation Comma-separated list of values. Can include:owner, collaborator, organization_member
     * @param sort Can be one of all, owner, public, private, member
     * @return
     */
    @GET("/user/repos")
    Observable<List<RepositoriesBean>> getOwendRespositories(@Header("Authorization") String auth
            , @Nullable @Query("affiliation") List<String> affiliation
            , @Nullable @Query("sort") String sort);
}
