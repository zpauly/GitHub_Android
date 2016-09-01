package com.zpauly.githubapp.network.issues;


import android.support.annotation.Nullable;

import com.zpauly.githubapp.entity.response.issues.IssueBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
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

    @GET("/issues")
    Observable<List<IssueBean>> getIssues(@Header("Authorization") String auth,
                                          @Nullable @Query("filter") String filter,
                                          @Nullable @Query("state") String state,
                                          @Nullable @Query("labels") String labels,
                                          @Nullable @Query("sort") String sort,
                                          @Nullable @Query("direction") String dir,
                                          @Nullable @Query("since") String since);

    @GET("/user/issues")
    Observable<List<IssueBean>> getUserIssues(@Header("Authorization") String auth,
                                              @Nullable @Query("filter") String filter,
                                              @Nullable @Query("state") String state,
                                              @Nullable @Query("labels") String labels,
                                              @Nullable @Query("sort") String sort,
                                              @Nullable @Query("direction") String dir,
                                              @Nullable @Query("since") String since);

    @GET("/orgs/{org}/issues")
    Observable<List<IssueBean>> getOrgIssues(@Header("Authorization") String auth,
                                             @Path("org") String org,
                                             @Nullable @Query("filter") String filter,
                                             @Nullable @Query("state") String state,
                                             @Nullable @Query("labels") String labels,
                                             @Nullable @Query("sort") String sort,
                                             @Nullable @Query("direction") String dir,
                                             @Nullable @Query("since") String since);
}
