package com.example.myapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieResponse (@SerializedName("results") @Expose var listMovie : List<Movie>,
                          @SerializedName("page") @Expose var page : Integer,
                          @SerializedName("total_results") @Expose var totalResults : Integer,
                          @SerializedName("dates") @Expose var dates : Dates,
                          @SerializedName("total_pages") @Expose var totalPages : Integer)