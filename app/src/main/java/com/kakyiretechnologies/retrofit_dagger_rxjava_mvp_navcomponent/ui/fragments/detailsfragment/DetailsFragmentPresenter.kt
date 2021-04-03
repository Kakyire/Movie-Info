package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.detailsfragment

import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.model.MovieResults
import javax.inject.Inject

class DetailsFragmentPresenter @Inject constructor(private var view: DetailsFragmentContract.View?) :
    DetailsFragmentContract.Presenter {

    @Inject
    lateinit var model: DetailsFragmentModel

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
        /*
        we won't call this function instead loadMoreMovies function
         because we have to pass argument to load the recommendation movies
        */
    }

    override fun loadMoreMovies(pageNo: Int) {
        model.getMoviesFromServer(pageNo, this)
    }

    override fun onDestroy() {
        view = null
    }
}