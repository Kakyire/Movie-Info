package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.component

import android.content.Context
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.context.ApplicationContext
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.module.NetworkModule
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.scope.ApplicationScope
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.network.ApiInterface
import dagger.Component

@ApplicationScope
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {


    fun getApiInterface(): ApiInterface

    @ApplicationContext
   fun getApplicationContext(): Context

}