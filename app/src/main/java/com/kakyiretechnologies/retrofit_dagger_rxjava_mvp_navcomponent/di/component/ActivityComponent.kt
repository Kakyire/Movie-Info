package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.component

import android.content.Context
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.context.ActivityContext
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.module.FragmentContextModule
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.module.AdapterModule
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.module.ViewModule
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.scope.ActivityScope
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.popularmovies.PopularMoviesFragment
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.topratemovies.TopRatedMoviesFragment
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.trendingmovies.TrendingMoviesFragment
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.upcomingmovies.UpcomingMoviesFragment
import dagger.Component

@ActivityScope
@Component(
    modules = [FragmentContextModule::class, ViewModule::class, AdapterModule::class],
    dependencies = [ApplicationComponent::class]
)
interface ActivityComponent {

    fun inject(fragment: TrendingMoviesFragment)
    fun inject(fragment: TopRatedMoviesFragment)
    fun inject(fragment: UpcomingMoviesFragment)
    fun inject(fragment: PopularMoviesFragment)


    @ActivityContext
    fun getContext(): Context
}
