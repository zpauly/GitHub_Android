package com.zpauly.githubapp.network.gitdata;

import com.zpauly.githubapp.entity.response.BlobBean;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by zpauly on 16-8-3.
 */

public interface GitDataService {
    /**
     * Get a Blob
     * @param auth
     * @param owner
     * @param repo
     * @param sha
     * @return
     */
    @GET("/repos/{owner}/{repo}/git/blobs/{sha}")
    Observable<String> getABlob(@Header("Authorization") String auth,
                                  @Header("accept") String acc,
                                  @Path("owner") String owner,
                                  @Path("repo") String repo,
                                  @Path("sha") String sha);
}
