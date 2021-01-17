package com.example.base_project.main;

import androidx.lifecycle.MutableLiveData;

import com.example.base_project.base.BaseViewModel;
import com.example.base_project.base.baseApi.ApiRepository;
import com.example.base_project.base.baseApi.ApiUtils;
import com.example.base_project.main.model.Movie;
import com.example.base_project.upcoming.model.MovieResponse;

import org.checkerframework.checker.units.qual.A;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.example.base_project.Utils.API_KEY;

public class MainViewModel extends BaseViewModel {
    private MutableLiveData<String> title = new MutableLiveData<>();
    private MutableLiveData<List<Movie>> listFilmSearch = new MutableLiveData<>();

    public MutableLiveData<List<Movie>> getListFilmSearch() {
        return listFilmSearch;
    }

    public void setListFilmSearch(MutableLiveData<List<Movie>> listFilmSearch) {
        this.listFilmSearch = listFilmSearch;
    }

    public MutableLiveData<String> getTitle() {
        return title;
    }

    public void setTitle(MutableLiveData<String> title) {
        this.title = title;
    }


    public void searchFilm(String textSearch) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("api_key", API_KEY);
        params.put("language", "en-US");
        params.put("page", "1");
        params.put("include_adult", "false");
        params.put("query", textSearch);

        ApiUtils.getDataApi().searchFilm(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieResponse movieResponse) {
                        if (movieResponse.getListMovie() != null){
                            listFilmSearch.setValue(movieResponse.getListMovie());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
