package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.upcomingmovies

import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.model.MovieResponse

interface UpcomingMoviesContract {

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