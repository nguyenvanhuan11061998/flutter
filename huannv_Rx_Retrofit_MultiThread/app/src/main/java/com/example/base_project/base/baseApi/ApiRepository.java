package com.example.base_project.base.baseApi;

import com.example.base_project.upcoming.model.UpcomingModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface ApiRepository {

    @GET("movie/upcoming")
    Call<UpcomingModel> getUpcomingMovie(@Field("api_key") String apiKey);

}
