package com.zpauly.githubapp.network.user;

import com.zpauly.githubapp.entity.response.AuthenticatedUser;

import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;

/**
 * Created by zpauly on 16-6-10.
 */
public interface UserService {
    @GET("/user")
    Observable<AuthenticatedUser> getAuthenticatedUser(@Header("Authorization") String auth);
}
