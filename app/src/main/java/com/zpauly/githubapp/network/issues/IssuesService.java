package com.zpauly.githubapp.network.issues;


import android.support.annotation.Nullable;

import com.zpauly.githubapp.entity.request.IssueCommentRequestBean;
import com.zpauly.githubapp.entity.request.IssueRequestBean;
import com.zpauly.githubapp.entity.response.AssigneeBean;
import com.zpauly.githubapp.entity.response.issues.IssueBean;
import com.zpauly.githubapp.entity.response.CommentBean;
import com.zpauly.githubapp.entity.response.issues.LabelBean;
import com.zpauly.githubapp.entity.response.MilestoneBean;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zpauly on 16/9/1.
 */
public interface IssuesService {
    String FILTER_ASSIGNED = "assigned"; //Default

    String FILTER_CREATED = "created";

    String FILTER_MENTIONED = "mentioned";

    String FILTER_SUBSCRIBED = "subscribed";

    String FILTER_ALL = "all";

    String STATE_OPEN = "open";//Default

    String STATE_CLOSED = "closed";

    String STATE_ALL = "all";

    String SORT_CREATED = "created";//Default

    String SORT_UPDATED = "updated";

    String SORT_COMMENTS = "comments";

    String DIRECTION_ASC = "asc";

    String DIRECTION_DESC = "desc";//Default

    String DUE_ON = "due_on";//Default

    String COMPLETENESS = "completeness";

    /**
     * List all issues assigned to the authenticated user across all visible repositories including owned repositories, member repositories, and organization repositories:
     * @param auth
     * @param filter
     * @param state
     * @param labels
     * @param sort
     * @param dir
     * @param since
     * @return
     */
    @Headers("Cache-Control: public, max-age=600")
    @GET("/issues")
    Observable<List<IssueBean>> getIssues(@Header("Authorization") String auth,
                                          @Header("Accept") String acc,
                                          @Nullable @Query("filter") String filter,
                                          @Nullable @Query("state") String state,
                                          @Nullable @Query("labels") String labels,
                                          @Nullable @Query("sort") String sort,
                                          @Nullable @Query("direction") String dir,
                                          @Nullable @Query("since") String since,
                                          @Nullable @Query("page") int pageId);

    /**
     * List all issues across owned and member repositories assigned to the authenticated user:
     * @param auth
     * @param filter
     * @param state
     * @param labels
     * @param sort
     * @param dir
     * @param since
     * @return
     */
    @Headers("Cache-Control: public, max-age=600")
    @GET("/user/issues")
    Observable<List<IssueBean>> getUserIssues(@Header("Authorization") String auth,
                                              @Header("Accept") String acc,
                                              @Nullable @Query("filter") String filter,
                                              @Nullable @Query("state") String state,
                                              @Nullable @Query("labels") String labels,
                                              @Nullable @Query("sort") String sort,
                                              @Nullable @Query("direction") String dir,
                                              @Nullable @Query("since") String since,
                                              @Nullable @Query("page") int pageId);

    /**
     * List all issues for a given organization assigned to the authenticated user:
     * @param auth
     * @param org
     * @param filter
     * @param state
     * @param labels
     * @param sort
     * @param dir
     * @param since
     * @return
     */
    @Headers("Cache-Control: public, max-age=600")
    @GET("/orgs/{org}/issues")
    Observable<List<IssueBean>> getOrgIssues(@Header("Authorization") String auth,
                                             @Header("Accept") String acc,
                                             @Path("org") String org,
                                             @Nullable @Query("filter") String filter,
                                             @Nullable @Query("state") String state,
                                             @Nullable @Query("labels") String labels,
                                             @Nullable @Query("sort") String sort,
                                             @Nullable @Query("direction") String dir,
                                             @Nullable @Query("since") String since,
                                             @Nullable @Query("page") int pageId);

    /**
     * List issues for a repository
     * @param auth
     * @param owner
     * @param repo
     * @param milestone
     * @param state DEFAULT:open
     * @param assignee
     * @param creator
     * @param mentioned
     * @param sort DEFAULT:created
     * @param direction DEFAULT:desc
     * @param since
     * @param labels
     * @return
     */
    @Headers("Cache-Control: public, max-age=600")
    @GET("/repos/{owner}/{repo}/issues")
    Observable<List<IssueBean>> getARepoIssues(@Header("Authorization") String auth,
                                               @Header("Accept") String acc,
                                               @Path("owner") String owner,
                                               @Path("repo") String repo,
                                               @Nullable @Query("milestone") String milestone,
                                               @Nullable @Query("state") String state,
                                               @Nullable @Query("assignee") String assignee,
                                               @Nullable @Query("creator") String creator,
                                               @Nullable @Query("mentioned") String mentioned,
                                               @Nullable @Query("sort") String sort,
                                               @Nullable @Query("direction") String direction,
                                               @Nullable @Query("since") String since,
                                               @Nullable @Query("labels") String[] labels,
                                               @Nullable @Query("page") int pageId);

    /**
     * Get a single issue
     * @param auth
     * @param owner
     * @param repo
     * @param number
     * @return
     */
    @Headers("Cache-Control: public, max-age=600")
    @GET("/repos/{owner}/{repo}/issues/{number}")
    Observable<IssueBean> getASingleIssue(@Header("Authorization") String auth,
                                          @Header("Accept") String acc,
                                          @Path("owner") String owner,
                                          @Path("repo") String repo,
                                          @Path("number") int number,
                                          @Nullable @Query("page") int pageId);

    /**
     * List comments on an issue
     * @param auth
     * @param owner
     * @param repo
     * @param number
     * @param since
     * @return
     */
    @Headers("Cache-Control: public, max-age=600")
    @GET("/repos/{owner}/{repo}/issues/{number}/comments")
    Observable<List<CommentBean>> getAnIssueComments(@Header("Authorization") String auth,
                                                     @Header("Accept") String acc,
                                                     @Path("owner") String owner,
                                                     @Path("repo") String repo,
                                                     @Path("number") int number,
                                                     @Nullable @Query("since") String since,
                                                     @Nullable @Query("page") int pageId);

    /**
     * Create an issue
     * @param auth
     * @param issueRequestBean
     * @param owner
     * @param repo
     * @return
     */
    @POST("/repos/{owner}/{repo}/issues")
    Observable<IssueBean> createAnIssue(@Header("Authorization") String auth,
                                                                            @Body IssueRequestBean issueRequestBean,
                                                                            @Path("owner") String owner,
                                                                            @Path("repo") String repo);

    /**
     * Check assignee
     * You may also check to see if a particular user is an assignee for a repository.
     * @param auth
     * @param owner
     * @param repo
     * @param assignee
     * @return
     */
    @GET("/repos/{owner}/{repo}/assignees/{assignee}")
    Observable<String> checkAssignee(@Header("Authorization") String auth, @Path("owner") String owner,
                             @Path("repo") String repo, @Path("assignee") String assignee);

    /**
     * List assignees
     * This call lists all the available assignees to which issues may be assigned.
     * @param auth
     * @param owner
     * @param repo
     * @return
     */
    @Headers("Cache-Control: public, max-age=600")
    @GET("/repos/{owner}/{repo}/assignees")
    Observable<List<AssigneeBean>> getAssignees(@Header("Authorization") String auth,
                                                @Path("owner") String owner,
                                                @Path("repo") String repo);

    /**
     * Create a comment
     * @param auth
     * @param issueCommentRequestBean
     * @param owner
     * @param repo
     * @param number
     * @return
     */
    @Headers("Cache-Control: public, max-age=600")
    @POST("/repos/{owner}/{repo}/issues/{number}/comments")
    Observable<CommentBean> createAComment(@Header("Authorization") String auth,
                                           @Body IssueCommentRequestBean issueCommentRequestBean,
                                           @Path("owner") String owner,
                                           @Path("repo") String repo,
                                           @Path("number") int number);

    /**
     * List milestones for a repository
     * @param auth
     * @param state
     * @param sort
     * @param direction
     * @return
     */
    @Headers("Cache-Control: public, max-age=600")
    @GET("/repos/{owner}/{repo}/milestones")
    Observable<List<MilestoneBean>> getMilestones(@Header("Authorization") String auth,
                                                 @Path("owner") String owner,
                                                 @Path("repo") String repo,
                                                 @Nullable @Query("state") String state,
                                                 @Nullable @Query("sort") String sort,
                                                 @Nullable @Query("direction") String direction,
                                                  @Query("page") int pageId);

    /**
     * List all labels for this repository
     * @param auth
     * @param owner
     * @param repo
     * @return
     */
    @Headers("Cache-Control: public, max-age=600")
    @GET("repos/{owner}/{repo}/labels")
    Observable<List<LabelBean>> getLabels(@Header("Authorization") String auth,
                                          @Path("owner") String owner,
                                          @Path("repo") String repo,
                                          @Query("page") int pageId);

    /**
     * Users with push access can lock an issue's conversation.
     * @param auth
     * @param owner
     * @param repo
     * @param number
     * @return
     */
    @PUT("/repos/{owner}/{repo}/issues/{number}/lock")
    Observable<String> lockAnIssue(@Header("Authorization") String auth,
                                   @Path("owner") String owner,
                                   @Path("repo") String repo,
                                   @Path("number") int number);

    /**
     * Users with push access can unlock an issue's conversation.
     * @param auth
     * @param owner
     * @param repo
     * @param number
     * @return
     */
    @DELETE("/repos/{owner}/{repo}/issues/{number}/lock")
    Observable<String> unlockAnIssue(@Header("Authorization") String auth,
                                     @Path("owner") String owner,
                                     @Path("repo") String repo,
                                     @Path("number") int number);
}
