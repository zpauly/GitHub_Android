package com.zpauly.githubapp.network.organizations;

import com.zpauly.githubapp.entity.response.OrganizationBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;

/**
 * Created by zpauly on 16-8-4.
 */

public interface OrganizationsService {
    @GET("/users/orgs")
    Observable<List<OrganizationBean>> getUserOrgs(@Header("Authorization") String auth);
}
