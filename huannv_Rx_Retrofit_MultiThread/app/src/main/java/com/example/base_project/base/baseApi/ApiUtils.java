package com.example.base_project.base.baseApi;

public class ApiUtils {
    public static final String BASE_URL = "https://api.themoviedb.org/3/";

    public static ApiRepository getDataApi(){
        return ApiBuilder.getApi(BASE_URL).create(ApiRepository.class);
    }
}
