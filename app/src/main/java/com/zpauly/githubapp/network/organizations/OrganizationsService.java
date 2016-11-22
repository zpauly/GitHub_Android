package com.zpauly.githubapp.network.organizations;

import com.zpauly.githubapp.entity.response.OrganizationBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zpauly on 16-8-4.
 */

public interface OrganizationsService {
    /**
     * List your organizations
     * @param auth
     * @return
     */
    @Headers("Cache-Control: public, max-age=600")
    @GET("/user/orgs")
    Observable<List<OrganizationBean>> getUserOrgs(@Header("Authorization") String auth,
                                                   @Query("page") int pageId);

    @Headers("Cache-Control: public, max-age=600")
    @GET("/users/{username}/orgs")
    Observable<List<OrganizationBean>> getUserOrgs(@Header("Authorization") String auth,
                                                   @Path("username") String username,
                                                   @Query("page") int pageId);
}
