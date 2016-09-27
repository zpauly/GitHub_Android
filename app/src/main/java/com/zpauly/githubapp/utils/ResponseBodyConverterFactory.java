package com.zpauly.githubapp.utils;

import retrofit2.Converter;

/**
 * Created by zpauly on 16/9/27.
 */

public final class ResponseBodyConverterFactory extends Converter.Factory {
    public static ResponseBodyConverterFactory create() {
        return new ResponseBodyConverterFactory();
    }


}
