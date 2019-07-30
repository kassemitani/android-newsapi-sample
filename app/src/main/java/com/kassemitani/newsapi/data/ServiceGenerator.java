package com.kassemitani.newsapi.data;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(Constants.getBaseUrl())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

    private static OkHttpClient.Builder client = new OkHttpClient.Builder();

    public static  <S> S createService( Class<S> serviceClass){
        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request= chain.request();
                Request.Builder requestBuilder = request.newBuilder()
                        .header("Accept", "application/json")
                        .method(request.method(), request.body());

                return chain.proceed(requestBuilder.build());
            }
        });

        return builder
                .build().create(serviceClass);
    }

}
