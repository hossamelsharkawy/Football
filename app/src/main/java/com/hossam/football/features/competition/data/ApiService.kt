package com.hossam.football.features.competition.data

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("competitions")
    suspend fun getList(): Response<CompetitionsResponse>
}