package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils

import android.app.Application
import android.content.Context
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.component.ApplicationComponent
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.component.DaggerApplicationComponent
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.context.ApplicationContext
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.module.NetworkModule
import javax.inject.Inject

class BaseApp : Application() {

    private lateinit var component: ApplicationComponent

    @Inject
    @ApplicationContext
    lateinit var context: Context

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
            .networkModule(NetworkModule(this))
            .build()

    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

}
