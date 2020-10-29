package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.fragments.upcomingmovies

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.R
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.adapters.MovieAdapter
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.model.MovieResults
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.ActivityComponentImple.implementComponent
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.ClickListener
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.Constants.ERROR
import kotlinx.android.synthetic.main.upcoming_movies_fragment.*
import javax.inject.Inject

class UpcomingMoviesFragment : Fragment(R.layout.upcoming_movies_fragment),
    UpcomingMoviesContract.View, ClickListener {


    @Inject
    lateinit var presenter: UpcomingMoviesPresenter

    @Inject
    lateinit var movieAdapter: MovieAdapter

    private var pageNo = 1
    private var isScrolling = true
    private val movieResults: MutableList<MovieResults> = ArrayList()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        implementComponent(this, this, this)
            .inject(this)

        rvUpcoming.layoutManager = GridLayoutManager(context, 2)
        rvUpcoming.adapter = movieAdapter

        presenter.getMovies()

        scrollListener()

    }

    //when the user reach the last item in the recyclerview load more
    //if the current page is not the last page
    private fun scrollListener() {

        val layoutManager = rvUpcoming.layoutManager as GridLayoutManager
        rvUpcoming.addOnScrollListener(object : RecyclerView.OnScrollListener() {

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

    override fun showProgress() {
        pbUpcoming.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pbUpcoming.visibility = View.INVISIBLE
    }

    override fun onFailed(t: Throwable) {
        Toast.makeText(context, ERROR, Toast.LENGTH_SHORT).show()
    }

    override fun loadRecyclerView(results: List<MovieResults>) {
        movieAdapter.loadData(results)
        movieResults.addAll(results)
        isScrolling = true

        //increase the pageNo so that we load from next page
        pageNo++
    }

    override fun onClick(position: Int) {
        val id = movieResults[position].id
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}