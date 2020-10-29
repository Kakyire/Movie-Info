package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.upcomingmovies

import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.model.MovieResults
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.BaseContract

interface UpcomingMoviesContract {

    interface Model : BaseContract.Model

    interface View : BaseContract.BaseView

    interface Presenter : BaseContract.Presenter
}