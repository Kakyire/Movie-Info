package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.model


import com.google.gson.annotations.SerializedName

data class RecommendationsResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val recommendationResults: List<RecommendationResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)