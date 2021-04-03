package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.detailsfragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.R
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.adapters.RecommendationsAdapter
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.listeners.ClickListener
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.model.MovieResults
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.activities.MainActivity
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.ActivityComponentImple.implementComponent
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.Constants.ERROR
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.LoadImageWithGlide.loadImage
import kotlinx.android.synthetic.main.details_fragment.*
import javax.inject.Inject


class DetailsFragment : Fragment(R.layout.details_fragment), DetailsFragmentContract.View,
    ClickListener {


    private val args by navArgs<DetailsFragmentArgs>()

    @Inject
    lateinit var presenter: DetailsFragmentPresenter

    @Inject
    lateinit var recommendationAdapter: RecommendationsAdapter

    private val recommendationResults: MutableList<MovieResults> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        implementComponent(activity as MainActivity, this, this)
            .inject(this)
        loadUI()
        presenter.loadMoreMovies(args.detailsFragmentArgs.id)
    }


    private fun loadUI() {

        rvRecommendation.apply {
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL, false
                )
            adapter = recommendationAdapter
        }
        args.detailsFragmentArgs.apply {
            tvOriginalTitle.text = title
            tvOverview.text = overview
            loadImage(requireContext(), ivPoster, posterPath)
            tvRelease.text = releaseDate

        }


    }


    override fun showProgress() {
        pbDetails.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pbDetails.visibility = View.INVISIBLE
    }

    override fun onFailed(t: Throwable) {
        Toast.makeText(requireContext(), ERROR, Toast.LENGTH_SHORT).show()
    }

    override fun loadRecyclerView(results: List<MovieResults>) {
        recommendationAdapter.loadResults(results)
        recommendationResults.addAll(results)
        tvRecommend.visibility = View.VISIBLE
    }

    override fun onClick(results: MovieResults) {

        findNavController().navigate(
            DetailsFragmentDirections.actionDetailFragmentToDetailFragment(
                results
            )
        )

    }
}