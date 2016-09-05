package com.zpauly.githubapp.network.search;

import android.support.annotation.Nullable;

import com.zpauly.githubapp.entity.response.search.SearchCodeBean;
import com.zpauly.githubapp.entity.response.search.SearchReposBean;
import com.zpauly.githubapp.entity.response.search.SearchUsersBean;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zpauly on 16-8-9.
 */

public interface SearchService {
    String SORT_STARS = "stars";

    String SORT_FORKS = "forks";

    String SORT_UPDATED = "updated";

    String ORDER_ASC = "asc";

    String ORDER_DESC = "desc";

    /**
     * Search repositories
     * Find repositories via various criteria. This method returns up to 100 results per page.
     * @param auth
     * @param sort
     * @param order
     * @return
     */
    @GET("/search/repositories")
    Observable<SearchReposBean> getSearchRepos(@Header("Authorization") String auth,
                                               @Query("q") String query,
                                               @Nullable @Query("sort") String sort,
                                               @Nullable @Query("order") String order);


    String SORT_INDEXED = "indexed";

    /**
     * Search code
     * Find file contents via various criteria. (This method returns up to 100 results per page.)
     * @param auth
     * @param sort
     * @param order
     * @return
     */
    @GET("/search/code")
    Observable<SearchCodeBean> getSearchCode(@Header("Authorization") String auth,
                                             @Query("q") String query,
                                             @Nullable @Query("sort") String sort,
                                             @Nullable @Query("order") String order);


    String SORT_REPOSITORIES = "repositories";

    String SORT_FOLLOWERS = "followers";

    String SORT_JOINED = "joined";

    /**
     * Search users
     *
     * @param auth
     * @param sort
     * @param order
     * @return
     */
    @GET("/search/users")
    Observable<SearchUsersBean> getSearchUsers(@Header("Authorization") String auth,
                                               @Query("q") String query,
                                               @Nullable @Query("sort") String sort,
                                               @Nullable @Query("order") String order);
}
