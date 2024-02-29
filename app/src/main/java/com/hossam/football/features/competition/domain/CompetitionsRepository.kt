package com.hossam.football.features.competition.domain

import com.hossam.football.features.competition.data.CompetitionDto

interface CompetitionsRepository {
    suspend fun getCompetitions(): List<CompetitionDto>
}