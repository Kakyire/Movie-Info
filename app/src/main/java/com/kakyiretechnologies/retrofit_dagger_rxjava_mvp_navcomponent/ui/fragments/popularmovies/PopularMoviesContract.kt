package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.popularmovies

import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.BaseContract

interface PopularMoviesContract {

    interface Model : BaseContract.Model

    interface View : BaseContract.BaseView

    interface Presenter : BaseContract.Presenter

}