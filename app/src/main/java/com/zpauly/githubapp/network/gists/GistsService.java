package com.zpauly.githubapp.network.gists;

import com.zpauly.githubapp.entity.response.gists.GistsBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
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
    Observable<List<GistsBean>> getUserGists(@Header("Authorization") String auth, @Query("page") int pageId);

    @GET("/users/{username}/gists")
    Observable<List<GistsBean>> getUserGists(@Header("Authorization") String auth,
                                             @Path("username") String username,
                                             @Query("page") int pageId);

    /**
     * List starred gists
     * @param auth
     * @param pageId
     * @return
     */
    @GET("/gists/starred")
    Observable<List<GistsBean>> getStarredGists(@Header("Authorization") String auth,
                                                @Query("page") int pageId);

    /**
     * List all public gists
     * @param auth
     * @param pageId
     * @return
     */
    @GET("/gists/public")
    Observable<List<GistsBean>> getPublicGists(@Header("Authorization") String auth,
                                               @Query("page") int pageId);

    /**
     * Get content of a gist file
     * @param auth
     * @param id1
     * @param id2
     * @return
     */
    @GET("/{id1}/{id2}")
    Observable<String> getGistFileContent(@Header("Authorization") String auth, @Path("id1") String id1,
                                          @Path("id2") String id2);
    @GET("/{filename}")
    Observable<String> getGistFullContent(@Header("Authorization") String auth,
                                          @Path("filename") String filename);

    @GET("/gists/{id}")
    Observable<GistsBean> getASingleGist(@Header("Authorization") String auth, @Path("id") String id);
}
