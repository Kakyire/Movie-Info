package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.listeners

import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.model.MovieResults

interface ClickListener {

    fun onClick(results:MovieResults)
}