package com.example.base_project.detailFim.model;

import com.example.base_project.upcoming.model.MovieResponse;

public class AllDetailFilmResponse {
    private GetVideoResponse getVideoResponse;
    private GetDetailResponse getDetailResponse;
    private MovieResponse getSimilarResponse;
    private MovieResponse getRecommendation;

    public MovieResponse getGetSimilarResponse() {
        return getSimilarResponse;
    }

    public void setGetSimilarResponse(MovieResponse getSimilarResponse) {
        this.getSimilarResponse = getSimilarResponse;
    }

    public MovieResponse getGetRecommendation() {
        return getRecommendation;
    }

    public void setGetRecommendation(MovieResponse getRecommendation) {
        this.getRecommendation = getRecommendation;
    }

    public GetDetailResponse getGetDetailResponse() {
        return getDetailResponse;
    }

    public void setGetDetailResponse(GetDetailResponse getDetailResponse) {
        this.getDetailResponse = getDetailResponse;
    }

    public GetVideoResponse getGetVideoResponse() {
        return getVideoResponse;
    }

    public void setGetVideoResponse(GetVideoResponse getVideoResponse) {
        this.getVideoResponse = getVideoResponse;
    }
}
