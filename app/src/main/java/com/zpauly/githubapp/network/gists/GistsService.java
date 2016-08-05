package com.zpauly.githubapp.network.gists;

import com.zpauly.githubapp.entity.response.gists.GistsBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by zpauly on 16-8-5.
 */

public interface GistsService {
    /**
     * List a user's gists
     * List the authenticated user's gists or if called anonymously,
     * this will return all public gists:
     * @param auth
     * @return
     */
    @GET("/gists")
    Observable<List<GistsBean>> getUserGists(@Header("Authorization") String auth);

    @GET("/users/{username}/gists")
    Observable<List<GistsBean>> getUserGists(@Header("Authorization") String auth,
                                             @Path("username") String username);
}
