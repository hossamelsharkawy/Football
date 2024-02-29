package com.hossam.football.features.competition.domain

import com.hossam.football.features.competition.data.CompetitionDto

interface CompetitionsLocalDataSource {
    suspend fun getCompetitions(): List<CompetitionDto>?
    suspend fun saveCompetitions(competitions: List<CompetitionDto>)
}