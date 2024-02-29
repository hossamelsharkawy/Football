package com.hossam.football.features.competition.data

import com.hossam.football.features.competition.domain.CompetitionsLocalDataSource
import com.hossam.football.features.competition.domain.CompetitionsRemoteDataSource
import com.hossam.football.features.competition.domain.CompetitionsRepository
import com.hossam.football.shared.DataFetchException
import com.hossam.football.shared.NetworkException
import javax.inject.Inject

class CompetitionsRepositoryImpl @Inject constructor(
    private val localDataSource: CompetitionsLocalDataSource,
    private val remoteDataSource: CompetitionsRemoteDataSource,
) : CompetitionsRepository {

    override suspend fun getCompetitions() = try {
        remoteDataSource.getCompetitions()
            .also { localDataSource.saveCompetitions(it) }
    } catch (e: NetworkException) {
        localDataSource.getCompetitions()
            ?: throw DataFetchException(
                e.message ?: e.stackTraceToString()
            )
    }
}
