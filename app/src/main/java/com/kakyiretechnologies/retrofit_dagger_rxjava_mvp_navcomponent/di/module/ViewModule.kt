package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.module

import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.scope.ActivityScope
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.BaseContract
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.detailsfragment.DetailsFragmentContract
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.popularmovies.PopularMoviesContract
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.topratemovies.TopRatedMoviesContract
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.trendingmovies.TrendingMoviesContract
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.upcomingmovies.UpcomingMoviesContract
import dagger.Module
import dagger.Provides


/**
 * since it is not possible to inject interface we have to create
 * provide functions for the various view interfaces which inherit parents are
 * @param baseView
 */
@Module
class ViewModule(private val baseView: BaseContract.BaseView) {


    @ActivityScope
    @Provides
    fun provideTrendingMovieView(): TrendingMoviesContract.View {
        return baseView as TrendingMoviesContract.View
    }

    @ActivityScope
    @Provides
    fun providePopularMovieView(): PopularMoviesContract.View {
        return baseView as PopularMoviesContract.View
    }

    @ActivityScope
    @Provides
    fun provideTopRateMovieView(): TopRatedMoviesContract.View {
        return baseView as TopRatedMoviesContract.View
    }

    @ActivityScope
    @Provides
    fun provideUpcomingMovieView(): UpcomingMoviesContract.View {
        return baseView as UpcomingMoviesContract.View
    }

    @ActivityScope
    @Provides
    fun provideDetailsActivityView(): DetailsFragmentContract.View {
        return baseView as DetailsFragmentContract.View
    }

}