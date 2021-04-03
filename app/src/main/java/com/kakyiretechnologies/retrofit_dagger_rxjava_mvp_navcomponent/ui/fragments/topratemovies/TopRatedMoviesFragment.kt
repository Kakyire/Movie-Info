package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.topratemovies

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.R
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.adapters.MovieAdapter
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.listeners.ClickListener
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.listeners.Listeners.onScrollListener
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.listeners.Listeners.refreshOnSwipe
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.model.MovieResults
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.activities.MainActivity
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.ActivityComponentImple.implementComponent
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.Constants.ERROR
import kotlinx.android.synthetic.main.toprated_movies_fragment.*
import javax.inject.Inject

class TopRatedMoviesFragment : Fragment(R.layout.toprated_movies_fragment),
    TopRatedMoviesContract.View, ClickListener {


    @Inject
    lateinit var movieAdapter: MovieAdapter

    @Inject
    lateinit var presenter: TopRatedMoviesPresenter

    private var canScroll = true
    private var pageNo = 1
    private val movieResults: MutableList<MovieResults> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        implementComponent(activity as MainActivity, this, this)
            .inject(this)

        recyclerViewSetup()
        presenter.getMovies()


        srTopRated.refreshOnSwipe(presenter)
    }

    private fun recyclerViewSetup() {
        rvRated.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = movieAdapter
            canScroll = this.onScrollListener(canScroll, pageNo, presenter)
        }

    }

    override fun showProgress() {
        pbTopRated.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pbTopRated.visibility = View.INVISIBLE
    }

    override fun onFailed(t: Throwable) {
        Toast.makeText(context, ERROR, Toast.LENGTH_SHORT).show()
    }

    override fun loadRecyclerView(results: List<MovieResults>) {

        movieAdapter.loadData(results)
        canScroll = true

        //increase the pageNo so that we load from next page
        pageNo++
    }

    override fun onClick(results: MovieResults) {

findNavController().navigate(TopRatedMoviesFragmentDirections
    .actionTopRatedMoviesFragmentToDetailsActivity(results))
    }


}