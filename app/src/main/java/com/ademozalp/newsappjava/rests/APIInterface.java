package com.ademozalp.newsappjava.rests;

import com.ademozalp.newsappjava.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("top-headlines")
    Call<ResponseModel> getLatestNews(@Query("country")String country, @Query("category") String category, @Query("apiKey") String apiKey);
    @GET("top-headlines")
    Call<ResponseModel> getTopNews(@Query("country") String source,@Query("apiKey") String apiKey);
}
