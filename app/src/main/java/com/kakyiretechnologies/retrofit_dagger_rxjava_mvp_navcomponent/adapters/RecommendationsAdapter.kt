package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.R
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.di.context.ActivityContext
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.model.RecommendationResult
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.ClickListener
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.Constants.IMAGE_URL
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils.LoadImageWithGlide.loadImage
import kotlinx.android.synthetic.main.recommendations_list.view.*
import javax.inject.Inject

class RecommendationsAdapter @Inject constructor(
    @ActivityContext private val context: Context,
    private val listener: ClickListener
) :
    RecyclerView.Adapter<RecommendationsAdapter.ViewHolder>() {


    private val list: MutableList<Any> = ArrayList()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recommendations_list, parent, false)
        )

    }

    fun loadResults(results: List<Any>) {
        list.addAll(results)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val recommendations = list[position] as RecommendationResult
        holder.itemView.tvRecommendation.text = recommendations.title
        val thumbnail = "$IMAGE_URL${recommendations.posterPath}"
        loadImage(context, holder.itemView.ivRecommendation, thumbnail)

        holder.itemView.setOnClickListener { listener.onClick(position) }

    }

    override fun getItemCount() = list.size


}