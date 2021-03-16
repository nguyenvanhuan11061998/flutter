package com.example.myapplication.base.baseApi

class ApiUtils {


    companion object {
        var BASE_URL : String = "https://api.themoviedb.org/3/";
        fun getDataApi(): ApiRepository? {
            return ApiBuilder.getApi(BASE_URL).create(ApiRepository::class.javaObjectType)
        }
    }

}