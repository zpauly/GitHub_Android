package com.zpauly.githubapp.utils;

import android.content.Context;

import com.zpauly.githubapp.Constants;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 16-5-7.
 */
public class RetrofitUtil {
    private static Context mContext;

    public static void setupContext(Context context) {
        Nested.setupContext(context);
    }

    private static OkHttpClient.Builder builder;

    static {
        File cacheDir = new File(mContext.getCacheDir(), "NetCache");
        Cache cache = new Cache(cacheDir, Constants.MAX_MEMORY / 1024);

        builder = new OkHttpClient.Builder();
        builder.cache(cache);
        builder.connectTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.followRedirects(true);
        builder.followSslRedirects(true);
        builder.addInterceptor(new NetCacheInterceptor(mContext));
    }

    public static Retrofit initRetrofit(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();

        return retrofit;
    }

    public static Retrofit initSimpleRetrofit(String baseUrl) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .followRedirects(true)
                .followSslRedirects(true);
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();

        return retrofit;
    }

    public static Retrofit initCustomRetrofit(String baseUrl, Converter.Factory converter,
                                              CallAdapter.Factory callAdapter) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(converter)
                .addCallAdapterFactory(callAdapter)
                .baseUrl(baseUrl)
                .build();

        return retrofit;
    }

    private static class Nested {
        static void setupContext(Context context) {
            mContext = context;
        }
    }
}
