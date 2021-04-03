package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.module

import android.content.Context
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.adapters.MovieAdapter
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.adapters.RecommendationsAdapter
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.scope.ActivityScope
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.listeners.ClickListener
import dagger.Module
import dagger.Provides

@Module
class AdapterModule(
    private val clickListener: ClickListener,
    private val context: Context
) {

    @Provides
    @ActivityScope
    fun provideClickListener(): ClickListener {
        return clickListener
    }

    @Provides
    @ActivityScope
    fun provideMovieAdapter(): MovieAdapter {
        return MovieAdapter(context, clickListener)
    }

    @Provides
    @ActivityScope
    fun provideRecommendationAdapter(): RecommendationsAdapter {
        return RecommendationsAdapter(context, clickListener)
    }
}
