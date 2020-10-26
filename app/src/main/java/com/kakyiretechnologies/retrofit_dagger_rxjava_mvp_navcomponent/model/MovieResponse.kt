package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.model

import java.io.Serializable

data class MovieResponse(
    val page: Int,
    val results: List<Result>,
    val totalPages: Int,
    val totalResults: Int
):Serializable