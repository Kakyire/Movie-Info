package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils

import android.content.Context
import android.content.Intent
import android.util.Log
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.model.MovieResults
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.activities.detailsactivity.DetailsActivity

object NavigateToDetails {
    private const val TAG = "results"
    fun navigate(context: Context?, results: MovieResults) {
        val intent = Intent(context, DetailsActivity::class.java)
        intent.apply {
            putExtra(Constants.MOVIE_ID, results.id)
            putExtra(Constants.MOVIE_OVERVIEW, results.overview)
            putExtra(Constants.MOVIE_TITLE, results.title)
            putExtra(Constants.POSTER, "${Constants.IMAGE_URL}${results.posterPath}")
            putExtra(Constants.RELEASE_DATE, results.releaseDate)
        }
        Log.d(TAG, "navigate: ${results.id}")
        context?.startActivity(intent)
    }
}