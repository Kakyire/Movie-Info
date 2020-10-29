package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.upcomingmovies

import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.model.MovieResponse
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.network.ApiInterface
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.BaseContract
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UpcomingMoviesModel @Inject constructor(private val apiInterface: ApiInterface) :
    UpcomingMoviesContract.Model {
    override fun getMoviesFromServer(pageNo: Int, presenter: BaseContract.Presenter) {

        apiInterface.upcomingMovies(pageNo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MovieResponse> {
                override fun onSubscribe(d: Disposable) {
                    presenter.onLoading()
                }

                override fun onNext(t: MovieResponse) {
                    presenter.onSuccess(t.movieResults)
                }

                override fun onError(e: Throwable) {
                    presenter.onError(e)
                }

                override fun onComplete() {
                    println("finished")
                }
            })
    }
}