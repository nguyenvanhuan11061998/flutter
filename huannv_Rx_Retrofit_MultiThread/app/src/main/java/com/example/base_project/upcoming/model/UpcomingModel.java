package com.example.base_project.upcoming.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpcomingModel {
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("dates")
    @Expose
    private Dates dates;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public class Result {

        @SerializedName("popularity")
        @Expose
        private Double popularity;
        @SerializedName("vote_count")
        @Expose
        private Integer voteCount;
        @SerializedName("video")
        @Expose
        private Boolean video;
        @SerializedName("poster_path")
        @Expose
        private String posterPath;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("adult")
        @Expose
        private Boolean adult;
        @SerializedName("backdrop_path")
        @Expose
        private Object backdropPath;
        @SerializedName("original_language")
        @Expose
        private String originalLanguage;
        @SerializedName("original_title")
        @Expose
        private String originalTitle;
        @SerializedName("genre_ids")
        @Expose
        private List<Integer> genreIds = null;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("vote_average")
        @Expose
        private Integer voteAverage;
        @SerializedName("overview")
        @Expose
        private String overview;
        @SerializedName("release_date")
        @Expose
        private String releaseDate;

        public Double getPopularity() {
            return popularity;
        }

        public void setPopularity(Double popularity) {
            this.popularity = popularity;
        }

        public Integer getVoteCount() {
            return voteCount;
        }

        public void setVoteCount(Integer voteCount) {
            this.voteCount = voteCount;
        }

        public Boolean getVideo() {
            return video;
        }

        public void setVideo(Boolean video) {
            this.video = video;
        }

        public String getPosterPath() {
            return posterPath == null ? "" : posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Boolean getAdult() {
            return adult;
        }

        public void setAdult(Boolean adult) {
            this.adult = adult;
        }

        public Object getBackdropPath() {
            return backdropPath;
        }

        public void setBackdropPath(Object backdropPath) {
            this.backdropPath = backdropPath;
        }

        public String getOriginalLanguage() {
            return originalLanguage == null ? "" : originalLanguage;
        }

        public void setOriginalLanguage(String originalLanguage) {
            this.originalLanguage = originalLanguage;
        }

        public String getOriginalTitle() {
            return originalTitle == null ? "" : originalTitle;
        }

        public void setOriginalTitle(String originalTitle) {
            this.originalTitle = originalTitle;
        }

        public List<Integer> getGenreIds() {
            return genreIds;
        }

        public void setGenreIds(List<Integer> genreIds) {
            this.genreIds = genreIds;
        }

        public String getTitle() {
            return title == null ? "" : title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Integer getVoteAverage() {
            return voteAverage;
        }

        public void setVoteAverage(Integer voteAverage) {
            this.voteAverage = voteAverage;
        }

        public String getOverview() {
            return overview == null ? "" : overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getReleaseDate() {
            return releaseDate == null ? "" : releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }
    }

    public class Dates {

        @SerializedName("maximum")
        @Expose
        private String maximum;
        @SerializedName("minimum")
        @Expose
        private String minimum;

        public String getMaximum() {
            return maximum == null ? "" : maximum;
        }

        public void setMaximum(String maximum) {
            this.maximum = maximum;
        }

        public String getMinimum() {
            return minimum == null ? "" : minimum;
        }

        public void setMinimum(String minimum) {
            this.minimum = minimum;
        }

    }
}