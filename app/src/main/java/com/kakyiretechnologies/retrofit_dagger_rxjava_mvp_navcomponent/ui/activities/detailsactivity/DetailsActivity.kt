package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.activities.detailsactivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.R
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.adapters.RecommendationsAdapter
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.model.RecommendationResult
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.ActivityComponentImple.implementComponent
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.ClickListener
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.Constants.ERROR
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.Constants.IMAGE_URL
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.Constants.MOVIE_ID
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.Constants.MOVIE_OVERVIEW
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.Constants.MOVIE_TITLE
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.Constants.POSTER
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.Constants.RELEASE_DATE
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.LoadImageWithGlide.loadImage
import kotlinx.android.synthetic.main.details_activity.*
import javax.inject.Inject


class DetailsActivity : AppCompatActivity(), DetailsActivityContract.View, ClickListener {


    @Inject
    lateinit var presenter: DetailsActivityPresenter

    @Inject
    lateinit var recommendationAdapter: RecommendationsAdapter

    private val recommendationResults: MutableList<Any> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)
        setSupportActionBar(detailsToolbar)
        implementComponent(this, this, this)
            .inject(this)

        loadUI()

        presenter.loadMoreMovies(intent.getIntExtra(MOVIE_ID, 0))
    }


    private fun loadUI() {

        rvRecommendation.apply {
            layoutManager =
                LinearLayoutManager(this@DetailsActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = recommendationAdapter
        }

        tvOriginalTitle.text = intent.getStringExtra(MOVIE_TITLE)
        tvOverview.text = intent.getStringExtra(MOVIE_OVERVIEW)
        intent.getStringExtra(POSTER)?.let { loadImage(this, ivPoster, it) }
        tvRelease.text = intent.getStringExtra(RELEASE_DATE)
    }


    override fun showProgress() {
        pbDetails.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pbDetails.visibility = View.INVISIBLE
    }

    override fun onFailed(t: Throwable) {
        Toast.makeText(this, ERROR, Toast.LENGTH_SHORT).show()
    }

    override fun loadRecyclerView(results: List<Any>) {
        recommendationAdapter.loadResults(results)
        recommendationResults.addAll(results)
        tvRecommend.visibility = View.VISIBLE
    }

    override fun onClick(position: Int) {

        val results = recommendationResults[position] as RecommendationResult
        val intent = Intent(this, DetailsActivity::class.java)
        intent.apply {
            putExtra(MOVIE_ID, results.id)
            putExtra(MOVIE_OVERVIEW, results.overview)
            putExtra(MOVIE_TITLE, results.title)
            putExtra(POSTER, "$IMAGE_URL${results.posterPath}")
            putExtra(RELEASE_DATE, results.releaseDate)
        }

        startActivity(intent)
    }
}