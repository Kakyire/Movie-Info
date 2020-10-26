package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.model.MovieResponse
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.ClickListener

class MovieAdapter(clickListener: ClickListener) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val list:MutableList<MovieResponse> = ArrayList()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }




}