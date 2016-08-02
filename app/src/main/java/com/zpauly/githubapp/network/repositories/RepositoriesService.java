package com.zpauly.githubapp.network.repositories;

import android.support.annotation.Nullable;

import com.zpauly.githubapp.entity.response.RepositoriesBean;
import com.zpauly.githubapp.entity.response.RepositoryContentBean;

import java.util.List;


import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zpauly on 16-7-13.
 */

public interface RepositoriesService {
    String AFFILIATION_OWNER = "owner";

    String AFFILAITION_COLLABORATOR = "collaborator";

    String AFFILIATION_ORGANIZATION_MEMBER = "organization_member";

    String SORT_ALL = "all";

    String SORT_OWNER = "owner";

    String SORT_PUBLIC = "public";

    String SORT_PRIVATE = "private";

    String SORT_MEMBER = "member";

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
    Observable<List<RepositoriesBean>> getOwendRepositories(@Header("Authorization") String auth
            , @Nullable @Query("affiliation") List<String> affiliation
            , @Nullable @Query("sort") String sort, @Query("page") int pageId);

    @GET("/users/{username}/repos")
    Observable<List<RepositoriesBean>> getRepositories(@Path("username") String username
            , @Nullable @Query("affiliation") List<String> affiliation
            , @Nullable @Query("sort") String sort, @Query("page") int pageId);

    //repo
    @GET("/repos/{username}/{repo}")
    Observable<RepositoriesBean> getRepository(@Path("username") String username, @Path("repo") String repo);

    //contents
    /**
     * Get contents
     * This method returns the contents of a file or directory in a repository.
     * @param owner
     * @param repo
     * @param path
     * @return
     */
    @GET("/repos/{owner}/{repo}/contents/{path}")
    Observable<List<RepositoryContentBean>> getRepositoryContent(@Nullable @Header("Accept") String acc,
                                                                 @Path("owner") String owner,
                                                                 @Path("repo") String repo, @Path("path") String path);

    @GET("/repos/{owner}/{repo}/contents/{path}")
    Observable<String> getFileContent(@Nullable @Header("Accept") String acc,
                                                                 @Path("owner") String owner,
                                                                 @Path("repo") String repo, @Path("path") String path);

    /**
     * Get the README
     * This method returns the preferred README for a repository.
     * @param owner
     * @param repo
     * @return
     */
    @GET("/repos/{owner}/{repo}/readme")
    Observable<String> getReadMe(@Header("Accept") String acc,
                                                @Path("owner") String owner, @Path("repo") String repo);
}
