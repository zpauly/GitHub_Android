package com.zpauly.githubapp.utils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by zpauly on 16-7-31.
 */

public final class StringConverterFactory extends Converter.Factory {
    public static StringConverterFactory create() {
        return new StringConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new StringResponseConverter();
    }

    class StringResponseConverter implements Converter<ResponseBody, String> {

        @Override
        public String convert(ResponseBody value) throws IOException {
            try {
                String string = value.string();
                return string;
            } finally {
                 value.close();
            }
        }
    }
}
