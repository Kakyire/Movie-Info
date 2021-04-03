package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.listeners

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.ui.BaseContract

object Listeners {

    //when the user reach the last item in the recyclerview load more
    //if the current page is not the last page
    fun RecyclerView.onScrollListener(
        canScroll: Boolean=false,
        pageNo: Int,
        presenter: BaseContract.Presenter
    ): Boolean {

        Log.d(TAG, "onScrollListener: $canScroll")
        val layoutManager = this.layoutManager as GridLayoutManager
        this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalCount = layoutManager.itemCount
                val lastItem = layoutManager.findLastVisibleItemPosition()


                if (canScroll && (lastItem == totalCount - 1)) {

                    presenter.loadMoreMovies(pageNo)
                }

            }
        })

        return canScroll
    }

    //refresh content when user swipe down
    fun SwipeRefreshLayout.refreshOnSwipe(presenter: BaseContract.Presenter) {
        this.setOnRefreshListener {
            this.isRefreshing = false
            presenter.getMovies()

        }
    }
    private const val TAG = "Listeners"

}