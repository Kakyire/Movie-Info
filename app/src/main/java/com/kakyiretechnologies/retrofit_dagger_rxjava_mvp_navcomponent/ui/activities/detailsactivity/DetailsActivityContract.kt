package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.activities.detailsactivity

import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.BaseContract

interface DetailsActivityContract {

    interface View : BaseContract.BaseView

    interface Model : BaseContract.Model

    interface Presenter : BaseContract.Presenter
}