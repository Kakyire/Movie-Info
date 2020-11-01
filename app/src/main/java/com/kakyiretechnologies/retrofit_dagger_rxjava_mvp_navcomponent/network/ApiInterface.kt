package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.network

import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.model.MovieResponse
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.model.RecommendationsResponse
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.Constants.API
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.Constants.API_KEY
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.Constants.MOVIE_RECOMMENDATIONS
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.Constants.POPULAR_MOVIES
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.Constants.TOP_RATED_MOVIES
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.Constants.TRENDING_MOVIES
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.Constants.UPCOMING_MOVIES
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET(TRENDING_MOVIES)
    fun trendingMovies(
        @Query("page") pageNo: Int,
        @Query(API) key: String? = API_KEY

    ): Observable<MovieResponse>

    @GET(POPULAR_MOVIES)
    fun popularMovies(
        @Query("page") pageNo: Int,
        @Query(API) key: String? = API_KEY
    ): Observable<MovieResponse>

    @GET(UPCOMING_MOVIES)
    fun upcomingMovies(
        @Query("page") pageNo: Int,
        @Query(API) key: String? = API_KEY
    ): Observable<MovieResponse>

    @GET(TOP_RATED_MOVIES)
    fun topRatedMovies(
        @Query("page") pageNo: Int,
        @Query(API) key: String? = API_KEY
    ): Observable<MovieResponse>

    @GET(MOVIE_RECOMMENDATIONS)
    fun movieRecommendations(
        @Path("movie_id") movieId: Int, @Query("page") pageNo: Int? = 1,
        @Query(API) key: String? = API_KEY
    ): Observable<RecommendationsResponse>

}