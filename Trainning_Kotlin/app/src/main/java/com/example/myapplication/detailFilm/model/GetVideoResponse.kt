package com.example.myapplication.detailFilm.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetVideoResponse {
    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("results")
    @Expose
    private var results: List<GetVideoModel?>? = null

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getResults(): List<GetVideoModel?>? {
        return results
    }

    fun setResults(results: List<GetVideoModel?>?) {
        this.results = results
    }

    class GetVideoModel {
        @SerializedName("id")
        @Expose
        var id: String? = null
            get() = if (field == null) "" else field

        @SerializedName("iso_639_1")
        @Expose
        var iso6391: String? = null
            get() = if (field == null) "" else field

        @SerializedName("iso_3166_1")
        @Expose
        var iso31661: String? = null
            get() = if (field == null) "" else field

        @SerializedName("key")
        @Expose
        var key: String? = null
            get() = if (field == null) "" else field

        @SerializedName("name")
        @Expose
        var name: String? = null
            get() = if (field == null) "" else field

        @SerializedName("site")
        @Expose
        var site: String? = null
            get() = if (field == null) "" else field

        @SerializedName("size")
        @Expose
        var size: Int? = null

        @SerializedName("type")
        @Expose
        var type: String? = null
            get() = if (field == null) "" else field

    }
}