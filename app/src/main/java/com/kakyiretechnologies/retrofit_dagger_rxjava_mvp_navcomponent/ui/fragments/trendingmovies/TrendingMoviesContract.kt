package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.trendingmovies

import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.model.MovieResults
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.BaseContract

interface TrendingMoviesContract {



    interface Model : BaseContract.Model

    interface View : BaseContract.BaseView

    interface Presenter : BaseContract.Presenter
}