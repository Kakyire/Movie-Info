package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.module

import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.scope.ApplicationScope
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.network.ApiInterface
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * since we don't own Retrofit we have to create provides function for it
 * the same applies to ApiInterface in other to inject them
 */

@Module
class NetworkModule {

    @ApplicationScope
    @Provides
    fun retrofitProvide():Retrofit{

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }


    @ApplicationScope
    @Provides
    fun apiInterfaceProvides(retrofit: Retrofit):ApiInterface{
        return retrofit.create(ApiInterface::class.java)
    }
}