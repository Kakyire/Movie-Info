package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.detailsfragment

import android.util.Log
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.model.MovieResponse
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.network.ApiInterface
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.BaseContract
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailsFragmentModel @Inject constructor(private val apiInterface: ApiInterface) :
    DetailsFragmentContract.Model {
    private val TAG = "results"

    override fun getMoviesFromServer(pageNo: Int, presenter: BaseContract.Presenter) {

        apiInterface.movieRecommendations(pageNo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MovieResponse> {
                override fun onSubscribe(d: Disposable) {
                    presenter.onLoading()
                }

                override fun onNext(t: MovieResponse) {
                    presenter.onSuccess(t.movieResults)
                    Log.d(TAG, "onNext: ")
                }

                override fun onError(e: Throwable) {
                    presenter.onError(e)
                    Log.d(TAG, "onError: ${e.printStackTrace()}")
                }

                override fun onComplete() {
                    println()
                }
            })

    }
}