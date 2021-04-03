package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.upcomingmovies

import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.model.MovieResults
import javax.inject.Inject

class UpcomingMoviesPresenter @Inject constructor(private var view: UpcomingMoviesContract.View?) :
    UpcomingMoviesContract.Presenter {

    @Inject
    lateinit var model: UpcomingMoviesModel

    private var mutableList = emptyList<MovieResults>()

    fun loadMovies() {
        if (mutableList.isNotEmpty() && view != null) {
            view!!.loadRecyclerView(mutableList)
        } else {
            model.getMoviesFromServer(1, this)
        }
    }

    override fun onSuccess(results: List<MovieResults>) {
        view?.apply {
            hideProgress()
            mutableList = results
//            loadRecyclerView(results)
        }
    }


    override fun onError(t: Throwable) {
        view?.apply {
            hideProgress()
            onFailed(t)
        }
    }

    override fun onLoading() {
        view?.showProgress()
    }

    override fun getMovies() {
        loadMovies()
    }

    override fun loadMoreMovies(pageNo: Int) {
        model.getMoviesFromServer(pageNo, this)
    }

    override fun onDestroy() {
        view = null
    }
}