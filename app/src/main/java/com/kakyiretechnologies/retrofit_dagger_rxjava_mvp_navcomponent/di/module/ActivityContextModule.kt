package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.module

import android.app.Activity
import android.content.Context
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.context.ActivityContext
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.scope.ActivityScope
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.activities.MainActivity
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.detailsfragment.DetailsFragment
import dagger.Module
import dagger.Provides

@Module
class ActivityContextModule(
    private val activity: Activity
) {
    var context: Context = activity


    @Provides
    @ActivityScope
    @ActivityContext
    fun provideContext(): Context {
        return context
    }


//    @ActivityScope
//    @Provides
//    fun provideDetailsActivity(): DetailsFragment {
//        return activity as DetailsFragment
//    }

    @ActivityScope
    @Provides
    fun provideMainActivity(): MainActivity {
        return activity as MainActivity
    }

}