package com.example.base_project.base.baseApi;

import com.example.base_project.upcoming.model.MovieResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiRepository {

    @GET("movie/upcoming")
    Call<MovieResponse> getUpcomingMovies(@QueryMap Map<String, String> params);

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@QueryMap Map<String, String> params);

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@QueryMap Map<String, String> params);

    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlayingMovies(@QueryMap Map<String, String> params);

}
