package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.module

import android.content.Context
import androidx.fragment.app.Fragment
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.adapters.MovieAdapter
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.context.ActivityContext
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.scope.ActivityScope
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.activities.MainActivity
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.popularmovies.PopularMoviesFragment
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.topratemovies.TopRatedMoviesFragment
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.trendingmovies.TrendingMoviesContract
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.trendingmovies.TrendingMoviesFragment
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.upcomingmovies.UpcomingMoviesFragment
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.ClickListener
import dagger.Module
import dagger.Provides

@Module
class FragmentContextModule(
    private val fragment: Fragment
) {
    var context: Context = fragment.requireContext()


    @Provides
    @ActivityScope
    @ActivityContext
    fun provideContext(): Context {
        return context
    }

    @Provides
    @ActivityScope
    fun provideTrendingFragment():
            TrendingMoviesFragment {
        return fragment as TrendingMoviesFragment
    }

    @Provides
    @ActivityScope
    fun providePopularFragment(): PopularMoviesFragment {
        return fragment as PopularMoviesFragment
    }

    @Provides
    @ActivityScope
    fun provideUpcomingFragment(): UpcomingMoviesFragment {
        return fragment as UpcomingMoviesFragment
    }

    @Provides
    @ActivityScope
    fun provideTopRatedFragment(): TopRatedMoviesFragment {
        return fragment as TopRatedMoviesFragment
    }


}