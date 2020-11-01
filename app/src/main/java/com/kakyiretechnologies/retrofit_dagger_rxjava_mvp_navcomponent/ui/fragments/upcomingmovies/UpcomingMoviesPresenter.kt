package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.upcomingmovies

import javax.inject.Inject

class UpcomingMoviesPresenter @Inject constructor(private var view: UpcomingMoviesContract.View?) :
    UpcomingMoviesContract.Presenter {

    @Inject
    lateinit var model: UpcomingMoviesModel

    override fun onSuccess(results: List<Any>) {
        view?.apply {
            hideProgress()
            loadRecyclerView(results)
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
        model.getMoviesFromServer(1, this)
    }

    override fun loadMoreMovies(pageNo: Int) {
        model.getMoviesFromServer(pageNo, this)
    }

    override fun onDestroy() {
        view = null
    }
}