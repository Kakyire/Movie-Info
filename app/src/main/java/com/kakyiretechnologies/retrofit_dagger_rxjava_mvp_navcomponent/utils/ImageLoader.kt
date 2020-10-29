package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.R

object LoadImageWithGlide {

    fun loadImage(context: Context, imageView: ImageView, url: String) {

        if (url.isNotEmpty()) {
            Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView)
        } else {
            Glide.with(context)
                .load(R.mipmap.ic_launcher)
                .into(imageView)
        }
    }
}