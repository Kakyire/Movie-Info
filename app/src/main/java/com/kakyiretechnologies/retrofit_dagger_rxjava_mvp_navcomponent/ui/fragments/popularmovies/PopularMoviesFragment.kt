package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.popularmovies

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.R
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.adapters.MovieAdapter
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.model.MovieResults
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.activities.MainActivity
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.ActivityComponentImple.implementComponent
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.ClickListener
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.Constants.ERROR
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.NavigateToDetails.navigate
import kotlinx.android.synthetic.main.popular_movies_fragment.*
import javax.inject.Inject

class PopularMoviesFragment : Fragment(R.layout.popular_movies_fragment),
    PopularMoviesContract.View, ClickListener {


    @Inject
    lateinit var presenter: PopularMoviesPresenter

    @Inject
    lateinit var movieAdapter: MovieAdapter

    private var pageNo = 1
    private var isScrolling = true
    private val movieResults: MutableList<Any> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        implementComponent(activity as MainActivity, this, this)
            .inject(this)

        rvPopular.layoutManager = GridLayoutManager(context, 2)
        rvPopular.adapter = movieAdapter
        presenter.getMovies()
        scrollListener()
        refreshOnSwipe()
    }

    //when the user reach the last item in the recyclerview load more
    //if the current page is not the last page
    private fun scrollListener() {

        val layoutManager = rvPopular.layoutManager as GridLayoutManager
        rvPopular.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalCount = layoutManager.itemCount
                val lastItem = layoutManager.findLastVisibleItemPosition()

                if (isScrolling && (lastItem == totalCount - 1)) {
                    isScrolling = false
                    presenter.loadMoreMovies(pageNo)
                }
            }
        })
    }


    //refresh content when user swipe down
    private fun refreshOnSwipe() {
        srPopular.setOnRefreshListener {
            srPopular.isRefreshing = false
            presenter.getMovies()
        }

    }

    override fun showProgress() {
        pbPopular.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pbPopular.visibility = View.INVISIBLE
    }

    override fun onFailed(t: Throwable) {
        Toast.makeText(context, ERROR, Toast.LENGTH_SHORT).show()
    }

    override fun loadRecyclerView(results: List<Any>) {
        movieAdapter.loadData(results)
        movieResults.addAll(results)
        isScrolling = true

        //increase the pageNo so that we load from next page
        pageNo++
    }

    override fun onClick(position: Int) {
        val results = movieResults[position] as MovieResults
        navigate(context, results)

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}