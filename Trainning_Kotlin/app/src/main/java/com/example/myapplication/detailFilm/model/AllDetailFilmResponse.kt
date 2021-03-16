package com.example.myapplication.detailFilm.model

import com.example.myapplication.model.MovieResponse

class AllDetailFilmResponse {
    private var getVideoResponse: GetVideoResponse? = null
    private var getDetailResponse: GetDetailResponse? = null
    private var getSimilarResponse: MovieResponse? = null
    private var getRecommendation: MovieResponse? = null

    fun getGetSimilarResponse(): MovieResponse? {
        return getSimilarResponse
    }

    fun setGetSimilarResponse(getSimilarResponse: MovieResponse?) {
        this.getSimilarResponse = getSimilarResponse
    }

    fun getGetRecommendation(): MovieResponse? {
        return getRecommendation
    }

    fun setGetRecommendation(getRecommendation: MovieResponse?) {
        this.getRecommendation = getRecommendation
    }

    fun getGetDetailResponse(): GetDetailResponse? {
        return getDetailResponse
    }

    fun setGetDetailResponse(getDetailResponse: GetDetailResponse?) {
        this.getDetailResponse = getDetailResponse
    }

    fun getGetVideoResponse(): GetVideoResponse? {
        return getVideoResponse
    }

    fun setGetVideoResponse(getVideoResponse: GetVideoResponse?) {
        this.getVideoResponse = getVideoResponse
    }
}