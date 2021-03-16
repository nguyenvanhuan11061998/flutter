package com.example.myapplication.detailFilm.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.Utils.Companion.API_KEY
import com.example.myapplication.base.baseApi.ApiUtils
import com.example.myapplication.detailFilm.model.AllDetailFilmResponse
import com.example.myapplication.detailFilm.model.GetDetailResponse
import com.example.myapplication.detailFilm.model.GetVideoResponse
import com.example.myapplication.model.Movie
import com.example.myapplication.model.MovieResponse
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.*

class DetailFilmViewModel : ViewModel(){
    private val getVideoResponse: MutableLiveData<GetVideoResponse> =
        MutableLiveData<GetVideoResponse>()

    private val getDetailResponse: MutableLiveData<GetDetailResponse> =
        MutableLiveData<GetDetailResponse>()
    private val listSimilarMovie: MutableLiveData<List<Movie>> =
        MutableLiveData<List<Movie>>()
    private val listRecommendations: MutableLiveData<List<Movie>> =
        MutableLiveData<List<Movie>>()

    private var getDetailFilmFunction: Function<Array<Any>, AllDetailFilmResponse>? =
        null

    private fun callApiDetailFilm(idFilm: String) {
        val params: MutableMap<String, String> =
            HashMap()
        params["api_key"] = API_KEY
        val requests: MutableList<Observable<*>> =
            ArrayList()
        requests.add(
            ApiUtils.getDataApi()?.getVideoObs(idFilm, params)
                ?.subscribeOn(Schedulers.newThread())
                ?.observeOn(AndroidSchedulers.mainThread())!!
        )
        requests.add(
            ApiUtils.getDataApi()?.getDetailObs(idFilm, params)
                ?.subscribeOn(Schedulers.newThread())
                ?.observeOn(AndroidSchedulers.mainThread())!!
        )
        requests.add(
            ApiUtils.getDataApi()?.getSimilarMovie(idFilm, params)
                ?.subscribeOn(Schedulers.newThread())
                ?.observeOn(AndroidSchedulers.mainThread())!!
        )
        requests.add(
            ApiUtils.getDataApi()?.getRecommendationMovie(idFilm, params)
                ?.subscribeOn(Schedulers.newThread())
                ?.observeOn(AndroidSchedulers.mainThread())!!
        )
        getDetailFilmFunction =
            Function<Array<Any?>, Any?> { objects ->
                val allDetailFilmResponse = AllDetailFilmResponse()
                allDetailFilmResponse.setGetVideoResponse(objects[0] as GetVideoResponse?)
                allDetailFilmResponse.setGetDetailResponse(objects[1] as GetDetailResponse?)
                allDetailFilmResponse.setGetSimilarResponse(objects[2] as MovieResponse?)
                allDetailFilmResponse.setGetRecommendation(objects[3] as MovieResponse?)
                allDetailFilmResponse
            }

        val observer: Observer<AllDetailFilmResponse> =
            object : Observer<AllDetailFilmResponse> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(allDetailFilmResponse: AllDetailFilmResponse) {
                    if (allDetailFilmResponse.getGetVideoResponse() != null) {
                        getVideoResponse.setValue(allDetailFilmResponse.getGetVideoResponse())
                    }
                    if (allDetailFilmResponse.getGetDetailResponse() != null) {
                        getDetailResponse.setValue(allDetailFilmResponse.getGetDetailResponse())
                    }
                    if (allDetailFilmResponse.getGetSimilarResponse() != null) {
                        listSimilarMovie.setValue(
                            allDetailFilmResponse.getGetSimilarResponse()!!.listMovie
                        )
                    }
                    if (allDetailFilmResponse.getGetRecommendation() != null) {
                        listRecommendations.setValue(
                            allDetailFilmResponse.getGetRecommendation()!!.listMovie
                        )
                    }
                }

                override fun onError(e: Throwable) {}
                override fun onComplete() {}
            }
        Observable.zip(requests, getDetailFilmFunction).subscribe(observer)
    }

}