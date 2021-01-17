package com.example.base_project.base.baseApi;

import com.example.base_project.detailFim.model.GetDetailResponse;
import com.example.base_project.detailFim.model.GetVideoResponse;
import com.example.base_project.upcoming.model.MovieResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
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

    // ============================ RX Android =====================================================
    @GET("movie/{movie_id}/videos")
    Observable<GetVideoResponse> getVideoObs (@Path("movie_id") String movieId,
                                              @QueryMap Map<String, String> params);

    @GET("movie/{movie_id}")
    Observable<GetDetailResponse> getDetailObs (@Path("movie_id") String movieId,
                                               @QueryMap Map<String, String> params);

    @GET("movie/{movie_id}/similar")
    Observable<MovieResponse> getSimilarMovie (@Path("movie_id") String movieId,
                                              @QueryMap Map<String, String> params);

    @GET("movie/{movie_id}/recommendations")
    Observable<MovieResponse> getRecommendationMovie (@Path("movie_id") String movieId,
                                                      @QueryMap Map<String, String> params);

    @GET("search/movie")
    Observable<MovieResponse> searchFilm (@QueryMap Map<String, String> params);

}
