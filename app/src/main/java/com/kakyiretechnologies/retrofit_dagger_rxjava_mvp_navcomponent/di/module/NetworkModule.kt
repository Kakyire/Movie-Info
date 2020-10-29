package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.module

import android.content.Context
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.context.ApplicationContext
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.scope.ApplicationScope
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.network.ApiInterface
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.BaseApp
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * it is not possible to inject third party libraries and interfaces
 *  we have to create provides function for them before
 */

@Module
class NetworkModule(baseApp: BaseApp) {

    private val context: Context = baseApp

    @ApplicationScope
    @Provides
    fun retrofitProvide(): Retrofit {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }


    @ApplicationScope
    @Provides
    fun apiInterfaceProvides(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }


    @Provides
    @ApplicationScope
    @ApplicationContext
    fun provideApplicationContext(): Context {
        return context
    }


}