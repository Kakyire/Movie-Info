package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.component

import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.module.NetworkModule
import dagger.Component

@Component(modules = [NetworkModule::class])
interface ApplicationComponent {

}