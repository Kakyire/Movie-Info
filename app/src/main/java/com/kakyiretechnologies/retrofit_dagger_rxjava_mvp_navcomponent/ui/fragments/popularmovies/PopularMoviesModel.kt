package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.popularmovies

import android.util.Log
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.model.MovieResponse
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.network.ApiInterface
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.BaseContract
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PopularMoviesModel @Inject constructor(private val apiInterface: ApiInterface) :
    PopularMoviesContract.Model {
    private val TAG = "Results"
    override fun getMoviesFromServer(pageNo: Int, presenter: BaseContract.Presenter) {

        apiInterface.popularMovies(pageNo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MovieResponse> {
                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "onSubscribe: ")
                    presenter.onLoading()
                }

                override fun onNext(t: MovieResponse) {
                    presenter.onSuccess(t.movieResults)
                    Log.d(TAG, "onNext: ${t.movieResults}")
                }

                override fun onError(e: Throwable) {
                    presenter.onError(e)
                    Log.d(TAG, "onError: ${e.message}")
                }

                override fun onComplete() {
                    Log.d(TAG, "onComplete: ")
                }

            })
    }
}