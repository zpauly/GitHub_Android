package com.zpauly.githubapp.network.search;

import android.support.annotation.Nullable;

import com.zpauly.githubapp.entity.response.SearchReposBean;

import java.util.List;

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
    Observable<List<SearchReposBean>> getSearchRepos(@Header("Authorization") String auth,
                                                     @Nullable @Query("sort") String sort,
                                                     @Nullable @Query("order") String order);
}
