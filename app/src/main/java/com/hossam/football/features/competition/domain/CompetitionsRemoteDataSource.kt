package com.hossam.football.features.competition.domain

import com.hossam.football.features.competition.data.CompetitionDto

interface CompetitionsRemoteDataSource {
    suspend fun getCompetitions(): List<CompetitionDto>
}