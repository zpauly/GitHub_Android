package com.zpauly.githubapp.network.overview;

import com.zpauly.githubapp.Api;
import com.zpauly.githubapp.entity.request.AuthorizationRequest;
import com.zpauly.githubapp.entity.response.AppAuthorizationBean;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import rx.Observable;

/**
 * Created by zpauly on 16-6-8.
 */
public interface OverviewService {
    @PUT("/authorizations/clients/" + Api.CLIENT_ID)
    Observable<AppAuthorizationBean> login(@Header("Authorization") String auth,
                                           @Body AuthorizationRequest request);
}
