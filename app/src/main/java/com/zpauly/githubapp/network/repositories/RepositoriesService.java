package com.zpauly.githubapp.network.repositories;

import android.support.annotation.Nullable;

import com.zpauly.githubapp.entity.request.CommitCommentRequestBean;
import com.zpauly.githubapp.entity.response.CommentBean;
import com.zpauly.githubapp.entity.response.repos.BranchBean;
import com.zpauly.githubapp.entity.response.repos.ContributorBean;
import com.zpauly.githubapp.entity.response.repos.ReleaseBean;
import com.zpauly.githubapp.entity.response.repos.RepositoriesBean;
import com.zpauly.githubapp.entity.response.repos.RepositoryContentBean;
import com.zpauly.githubapp.entity.response.repos.SingleCommitBean;
import com.zpauly.githubapp.entity.response.repos.TagBean;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
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

    String TARBALL = "tarball";

    String ZIPBALL = "zipball";

    String NEWEST = "newest";

    String OLDEST = "oldest";

    String STARGAZERS = "stargazers";

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
    @Headers("Cache-Control: public, max-age=600")
    @GET("/user/repos")
    Observable<List<RepositoriesBean>> getOwendRepositories(@Header("Authorization") String auth
            , @Nullable @Query("affiliation") List<String> affiliation
            , @Nullable @Query("sort") String sort, @Query("page") int pageId);

    @Headers("Cache-Control: public, max-age=600")
    @GET("/users/{username}/repos")
    Observable<List<RepositoriesBean>> getRepositories(@Header("Authorization") String auth,
                                                       @Path("username") String username,
                                                       @Nullable @Query("affiliation") List<String> affiliation,
                                                       @Nullable @Query("sort") String sort,
                                                       @Query("page") int pageId);

    //repo
    @Headers("Cache-Control: public, max-age=600")
    @GET("/repos/{username}/{repo}")
    Observable<RepositoriesBean> getRepository(@Header("Authorization") String auth,
                                               @Path("username") String username,
                                               @Path("repo") String repo,
                                               @Query("ref") String ref);

    //contents
    /**
     * Get contents
     * This method returns the contents of a file or directory in a repository.
     * @param owner
     * @param repo
     * @param path
     * @return
     */
    @Headers("Cache-Control: public, max-age=600")
    @GET("/repos/{owner}/{repo}/contents/{path}")
    Observable<List<RepositoryContentBean>> getRepositoryContent(@Nullable @Header("Accept") String acc,
                                                                 @Header("Authorization") String auth,
                                                                 @Path("owner") String owner,
                                                                 @Path("repo") String repo,
                                                                 @Path("path") String path,
                                                                 @Query("ref") String ref);

    @Headers("Cache-Control: public, max-age=600")
    @GET("/repos/{owner}/{repo}/contents/{path}")
    Observable<String> getFileContent(@Header("Authorization") String auth,
                                      @Nullable @Header("Accept") String acc,
                                      @Path("owner") String owner,
                                      @Path("repo") String repo,
                                      @Path("path") String path,
                                      @Query("branch") String branch);

    /**
     * Get the README
     * This method returns the preferred README for a repository.
     * @param owner
     * @param repo
     * @return
     */
    @Headers("Cache-Control: public, max-age=600")
    @GET("/repos/{owner}/{repo}/readme")
    Observable<String> getReadMe(@Header("Authorization") String auth,
                                 @Header("Accept") String acc,
                                 @Path("owner") String owner, @Path("repo") String repo,
                                 @Query("ref") String ref);

    /**
     * List commits on a repository
     * @param auth
     * @param owner
     * @param repo
     * @param pageId
     * @return
     */
    @Headers("Cache-Control: public, max-age=600")
    @GET("/repos/{owner}/{repo}/commits")
    Observable<List<SingleCommitBean>> getRepositoryCommit(@Header("Authorization") String auth,
                                                           @Path("owner") String owner,
                                                           @Path("repo") String repo,
                                                           @Query("page") int pageId);

    /**
     * Get a single commit
     * @param auth
     * @param owner
     * @param repo
     * @param sha
     * @return
     */
    @Headers("Cache-Control: public, max-age=600")
    @GET("repos/{owner}/{repo}/commits/{sha}")
    Observable<SingleCommitBean> getASingleCommit(@Header("Authorization") String auth,
                                                  @Path("owner") String owner,
                                                  @Path("repo") String repo,
                                                  @Path("sha") String sha);

    /**
     * List commit comments for a repository
     * @param auth
     * @param owner
     * @param repo
     * @param ref
     * @param pageId
     * @return
     */
    @Headers("Cache-Control: public, max-age=600")
    @GET("/repos/{owner}/{repo}/commits/{ref}/comments")
    Observable<List<CommentBean>> getACommitComments(@Header("Authorization") String auth,
                                                     @Path("owner") String owner,
                                                     @Path("repo") String repo,
                                                     @Path("ref") String ref,
                                                     @Query("page") int pageId);

    /**
     * Create a commit comment
     * @param auth
     * @param owner
     * @param repo
     * @param sha
     * @param bean
     * @return
     */
    @POST("/repos/{owner}/{repo}/commits/{sha}/comments")
    Observable<CommentBean> createACommitComment(@Header("Authorization") String auth,
                                                 @Path("owner") String owner,
                                                 @Path("repo") String repo,
                                                 @Path("sha") String sha,
                                                 @Body CommitCommentRequestBean bean);

    /**
     * List releases for a repository
     * @param auth
     * @param owner
     * @param repo
     * @param pageId
     * @return
     */
    @Headers("Cache-Control: public, max-age=600")
    @GET("/repos/{owner}/{repo}/releases")
    Observable<List<ReleaseBean>> getRepositoryReleases(@Header("Authorization") String auth,
                                                        @Path("owner") String owner,
                                                        @Path("repo") String repo,
                                                        @Query("page") int pageId);

    /**
     * Get a single release
     * @param auth
     * @param owner
     * @param repo
     * @param id
     * @param pageId
     * @return
     */
    @GET("/repos/{owner}/{repo}/releases/{id}")
    Observable<ReleaseBean> getASingleRelease(@Header("Authorization") String auth,
                                              @Path("owner") String owner,
                                              @Path("repo") String repo,
                                              @Path("id") int id,
                                              @Query("page") int pageId);

    /**
     * List contributorsIntegrations Enabled
     * @param auth
     * @param owner
     * @param repo
     * @param pageId
     * @return
     */
    @Headers("Cache-Control: public, max-age=600")
    @GET("/repos/{owner}/{repo}/contributors")
    Observable<List<ContributorBean>> getContributors(@Header("Authorization") String auth,
                                                      @Path("owner") String owner,
                                                      @Path("repo") String repo,
                                                      @Query("page") int pageId);

    /**
     * Get archive link
     * @param auth
     * @param archive_format
     * @param ref
     * @return
     */
    @Streaming
    @GET("/repos/{owner}/{repo}/{archive_format}/{ref}")
    Observable<Response<ResponseBody>> getArchiveLink(@Header("Authorization") String auth,
                                                     @Path("owner") String owner,
                                                     @Path("repo") String repo,
                                                     @Path("archive_format") String archive_format,
                                                     @Nullable @Path("ref") String ref);

    /**
     * List Tags
     * @param auth
     * @param owner
     * @param repo
     * @return
     */
    @GET("/repos/{owner}/{repo}/tags?per_page=100")
    Observable<List<TagBean>> getTags(@Header("Authorization") String auth,
                                      @Path("owner") String owner,
                                      @Path("repo") String repo);

    /**
     * List Branches
     * @param auth
     * @param owner
     * @param repo
     * @return
     */
    @GET("/repos/{owner}/{repo}/branches?per_page=100")
    Observable<List<BranchBean>> getBranches(@Header("Authorization") String auth,
                                             @Path("owner") String owner,
                                             @Path("repo") String repo);

    /**
     * List forks
     * @param auth
     * @param owner
     * @param repo
     * @return
     */
    @GET("/repos/{owner}/{repo}/forks")
    Observable<List<RepositoriesBean>> getForks(@Header("Authorization") String auth,
                                                @Path("owner") String owner,
                                                @Path("repo") String repo,
                                                @Query("sort") String sort,
                                                @Query("page") int pageId);
}
