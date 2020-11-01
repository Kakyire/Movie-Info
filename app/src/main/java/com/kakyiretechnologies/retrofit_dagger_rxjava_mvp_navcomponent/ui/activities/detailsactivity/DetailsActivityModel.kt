package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.activities.detailsactivity

import android.util.Log
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.model.RecommendationsResponse
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.network.ApiInterface
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.BaseContract
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailsActivityModel @Inject constructor(private val apiInterface: ApiInterface) :
    DetailsActivityContract.Model {
    private val TAG = "results"

    override fun getMoviesFromServer(pageNo: Int, presenter: BaseContract.Presenter) {

        apiInterface.movieRecommendations(pageNo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<RecommendationsResponse> {
                override fun onSubscribe(d: Disposable) {
                    presenter.onLoading()
                }

                override fun onNext(t: RecommendationsResponse) {
                    presenter.onSuccess(t.recommendationResults)
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