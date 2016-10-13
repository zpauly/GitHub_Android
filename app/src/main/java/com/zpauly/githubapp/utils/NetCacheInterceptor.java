package com.zpauly.githubapp.utils;

import android.content.Context;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zpauly on 2016/10/13.
 */

public class NetCacheInterceptor implements Interceptor {
    private Context mContext;

    public NetCacheInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetUtil.isNetworkAvailable(mContext)) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }

        Response response = chain.proceed(request);
        if (NetUtil.isNetworkAvailable(mContext)) {
            response = response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", request.cacheControl().toString())
                    .build();
        } else {
            response = response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", CacheControl.FORCE_CACHE.toString())
                    .build();
        }
        return response;
    }
}
