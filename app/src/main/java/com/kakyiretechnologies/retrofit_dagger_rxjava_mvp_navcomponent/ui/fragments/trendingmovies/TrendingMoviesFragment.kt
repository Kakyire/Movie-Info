package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.trendingmovies

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.R
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.adapters.MovieAdapter
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.listeners.ClickListener
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.listeners.Listeners.refreshOnSwipe
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.model.MovieResults
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.activities.MainActivity
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.ActivityComponentImple.implementComponent
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.Constants.ERROR
import kotlinx.android.synthetic.main.trending_movies_fragment.*
import javax.inject.Inject

class TrendingMoviesFragment : Fragment(R.layout.trending_movies_fragment),
    TrendingMoviesContract.View, ClickListener {
    private val TAG = "Results"

    private var canScroll = true
    private var pageNo = 1

    @Inject
    lateinit var presenter: TrendingMoviesPresenter

    @Inject
    lateinit var movieAdapter: MovieAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        implementComponent(activity as MainActivity, this, this)
            .inject(this)

        recyclerViewSetup()
        presenter.getMovies()

//        rvTrending.onScrollListener(canScroll,pageNo, presenter)
        rvTrending.onScrollListener()
        srTrending.refreshOnSwipe(presenter)
    }

    private fun recyclerViewSetup() {
        rvTrending.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = movieAdapter
//            canScroll = this.onScrollListener(canScroll, pageNo, presenter)
        }

    }

    override fun showProgress() {
        pbTrending.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pbTrending.visibility = View.INVISIBLE
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
        findNavController().navigate(
            TrendingMoviesFragmentDirections
                .actionTrendingMoviesFragmentToDetailsActivity(results)
        )
    }

    private fun RecyclerView.onScrollListener(
//        canScroll: Boolean=false,
//        pageNo: Int,
//        presenter: BaseContract.Presenter
    ): Boolean {

        val layoutManager = this.layoutManager as GridLayoutManager
        this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalCount = layoutManager.itemCount
                val lastItem = layoutManager.findLastVisibleItemPosition()


                if (canScroll && (lastItem == totalCount - 1)) {
//                    canScroll = false
                    presenter.loadMoreMovies(pageNo)
                }

            }
        })

        return canScroll
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }
}