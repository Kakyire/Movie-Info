package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.topratemovies

import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.model.MovieResults
import javax.inject.Inject

class TopRatedMoviesPresenter @Inject constructor(private var view: TopRatedMoviesContract.View?) :
    TopRatedMoviesContract.Presenter {

    @Inject
    lateinit var model: TopRatedMoviesModel
    override fun onSuccess(results: List<MovieResults>) {
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