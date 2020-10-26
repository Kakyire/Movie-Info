package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.network

import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.model.MovieResponse
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.Constants.API
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.Constants.API_KEY
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.Constants.POPULAR_MOVIES
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.Constants.TOP_RATED_MOVIES
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.Constants.TRENDING_MOVIES
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.Constants.UPCOMING_MOVIES
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(TRENDING_MOVIES)
    fun trendingMovies(
        @Query(API) key: String = API_KEY,
        @Query("page") pageNo: Int
    ): Observable<MovieResponse>

    @GET(POPULAR_MOVIES)
    fun popularMovies(
        @Query(API) key: String = API_KEY,
        @Query("page") pageNo: Int
    ): Observable<MovieResponse>

    @GET(UPCOMING_MOVIES)
    fun upcomingMovies(
        @Query(API) key: String = API_KEY,
        @Query("page") pageNo: Int
    ): Observable<MovieResponse>

    @GET(TOP_RATED_MOVIES)
    fun topRatedMovies(
        @Query(API) key: String = API_KEY,
        @Query("page") pageNo: Int
    ): Observable<MovieResponse>


}