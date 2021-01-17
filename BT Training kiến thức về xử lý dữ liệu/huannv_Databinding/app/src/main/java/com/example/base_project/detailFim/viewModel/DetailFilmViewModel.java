package com.example.base_project.detailFim.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.base_project.base.BaseViewModel;
import com.example.base_project.base.baseApi.ApiUtils;
import com.example.base_project.detailFim.model.AllDetailFilmResponse;
import com.example.base_project.detailFim.model.GetDetailResponse;
import com.example.base_project.detailFim.model.GetVideoResponse;
import com.example.base_project.main.model.Movie;
import com.example.base_project.upcoming.model.MovieResponse;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.example.base_project.Utils.API_KEY;

public class DetailFilmViewModel extends BaseViewModel {

    private MutableLiveData<GetVideoResponse> getVideoResponse = new MutableLiveData<>();
    private MutableLiveData<GetDetailResponse> getDetailResponse = new MutableLiveData<>();
    private MutableLiveData<List<Movie>> listSimilarMovie = new MutableLiveData<>();
    private MutableLiveData<List<Movie>> listRecommendations = new MutableLiveData<>();

    public MutableLiveData<List<Movie>> getListRecommendations() {
        return listRecommendations;
    }

    public void setListRecommendations(MutableLiveData<List<Movie>> listRecommendations) {
        this.listRecommendations = listRecommendations;
    }

    public MutableLiveData<List<Movie>> getListSimilarMovie() {
        return listSimilarMovie;
    }

    public void setListSimilarMovie(MutableLiveData<List<Movie>> listSimilarMovie) {
        this.listSimilarMovie = listSimilarMovie;
    }

    public MutableLiveData<GetDetailResponse> getGetDetailResponse() {
        return getDetailResponse;
    }

    public void setGetDetailResponse(MutableLiveData<GetDetailResponse> getDetailResponse) {
        this.getDetailResponse = getDetailResponse;
    }

    private Function<Object[], AllDetailFilmResponse> getDetailFilmFunction;

    public MutableLiveData<GetVideoResponse> getGetVideoResponse() {
        return getVideoResponse;
    }

    public void setGetVideoResponse(MutableLiveData<GetVideoResponse> getVideoResponse) {
        this.getVideoResponse = getVideoResponse;
    }

    public void initDataViewModel(String idFilm) {
        callApiDetailFilm(idFilm);
    }

    private void callApiDetailFilm(String idFilm) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("api_key", API_KEY);

        List<Observable<?>> requests = new ArrayList<>();
        requests.add(ApiUtils.getDataApi().getVideoObs(idFilm, params)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread()));
        requests.add(ApiUtils.getDataApi().getDetailObs(idFilm, params)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()));
        requests.add(ApiUtils.getDataApi().getSimilarMovie(idFilm, params)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()));
        requests.add(ApiUtils.getDataApi().getRecommendationMovie(idFilm, params)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()));

        getDetailFilmFunction = new Function<Object[], AllDetailFilmResponse>() {

            @Override
            public AllDetailFilmResponse apply(Object[] objects) throws Exception {
                AllDetailFilmResponse allDetailFilmResponse = new AllDetailFilmResponse();
                allDetailFilmResponse.setGetVideoResponse((GetVideoResponse) objects[0]);
                allDetailFilmResponse.setGetDetailResponse((GetDetailResponse) objects[1]);
                allDetailFilmResponse.setGetSimilarResponse((MovieResponse) objects[2]);
                allDetailFilmResponse.setGetRecommendation((MovieResponse) objects[3]);
                return allDetailFilmResponse;
            }
        };

        Observer<AllDetailFilmResponse> observer = new Observer<AllDetailFilmResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(AllDetailFilmResponse allDetailFilmResponse) {
                if (allDetailFilmResponse.getGetVideoResponse() != null) {
                    getVideoResponse.setValue(allDetailFilmResponse.getGetVideoResponse());
                }
                if (allDetailFilmResponse.getGetDetailResponse() != null) {
                    getDetailResponse.setValue(allDetailFilmResponse.getGetDetailResponse());
                }
                if (allDetailFilmResponse.getGetSimilarResponse() != null) {
                    getListSimilarMovie().setValue(allDetailFilmResponse.getGetSimilarResponse().getListMovie());
                }
                if (allDetailFilmResponse.getGetRecommendation() != null) {
                    getListRecommendations().setValue(allDetailFilmResponse.getGetRecommendation().getListMovie());
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };

        Observable.zip(requests, getDetailFilmFunction).subscribe(observer);
    }
}
