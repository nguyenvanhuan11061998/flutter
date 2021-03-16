package com.example.myapplication.base.baseApi

import com.example.myapplication.detailFilm.model.GetDetailResponse
import com.example.myapplication.detailFilm.model.GetVideoResponse
import com.example.myapplication.model.MovieResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ApiRepository {
    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @QueryMap params : Map<String?, String?>
    ) : Call<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@QueryMap params: Map<String?, String?>?): Call<MovieResponse?>?

    @GET("movie/popular")
    fun getPopularMovies(@QueryMap params: Map<String?, String?>?): Call<MovieResponse?>?

    @GET("movie/now_playing")
    fun getNowPlayingMovies(@QueryMap params: Map<String?, String?>?): Call<MovieResponse?>?

    // ============================ RX Android =====================================================
    @GET("movie/{movie_id}/videos")
    fun getVideoObs(
        @Path("movie_id") movieId: String?,
        @QueryMap params: MutableMap<String, String>
    ): Observable<GetVideoResponse?>?

    @GET("movie/{movie_id}")
    fun getDetailObs(
        @Path("movie_id") movieId: String?,
        @QueryMap params: MutableMap<String, String>
    ): Observable<GetDetailResponse?>?

    @GET("movie/{movie_id}/similar")
    fun getSimilarMovie(
        @Path("movie_id") movieId: String?,
        @QueryMap params: MutableMap<String, String>
    ): Observable<MovieResponse?>?

    @GET("movie/{movie_id}/recommendations")
    fun getRecommendationMovie(
        @Path("movie_id") movieId: String?,
        @QueryMap params: MutableMap<String, String>
    ): Observable<MovieResponse?>?

    @GET("search/movie")
    fun searchFilm(@QueryMap params: MutableMap<String, String>): Observable<MovieResponse?>?
}