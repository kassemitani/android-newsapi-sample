package com.kassemitani.newsapi.data.service;

import com.kassemitani.newsapi.model.Info;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleService {

 //RX
 @GET("v2/everything")
 Flowable<Info> getInfoRX(@Query("q") String keyWord,
                          @Query("from") String from,
                          @Query("to") String to,
                          @Query("sortBy") String sortBy,
                          @Query("apiKey") String apiKey);
}
