package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.topratemovies

import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.model.MovieResponse

interface TopRatedMoviesContract {

    interface View {

        fun showProgress()
        fun hideProgress()
        fun onFailed(t: Throwable)
        fun onLoaded(movieResponse: MovieResponse)

    }

    interface Presenter {
        fun getMovies(pageNo: Int)
        fun onDestroy()

    }
}