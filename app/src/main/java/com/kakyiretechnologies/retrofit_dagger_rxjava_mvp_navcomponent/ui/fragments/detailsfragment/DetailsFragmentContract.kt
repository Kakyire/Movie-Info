package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.detailsfragment

import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.BaseContract

interface DetailsFragmentContract {

    interface View : BaseContract.BaseView

    interface Model : BaseContract.Model

    interface Presenter : BaseContract.Presenter
}