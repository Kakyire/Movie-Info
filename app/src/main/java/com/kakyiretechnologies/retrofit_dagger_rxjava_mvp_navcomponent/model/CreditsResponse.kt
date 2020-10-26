package com.kakyiretechnologies.retrofit_dagger_rxjava_mvp_navcomponent.model

data class CreditsResponse(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)