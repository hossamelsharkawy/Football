package com.hossam.football.features.competition.data

import com.hossam.football.features.competition.domain.CompetitionsRemoteDataSource
import com.hossam.football.shared.DataFetchException
import com.hossam.football.shared.NetworkException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.net.UnknownHostException
import javax.inject.Inject

class CompetitionsRemoteDataSourceImp @Inject constructor(
    private val apiService: ApiService,
) : CompetitionsRemoteDataSource {

    override suspend fun getCompetitions(): List<CompetitionDto>  {
       return try {
            val response = apiService.getList()

            if (response.isSuccessful) {
                response.body()?.competitions
                    ?: throw DataFetchException(response.body()?.toString() ?: "error")
            } else {
                throw NetworkException(response.body()?.toString() ?: "error")
            }
        } catch (e: Exception) {
            when (e) {
                is UnknownHostException -> throw NetworkException("No Internet Connection")
                else -> throw e
            }
        }
    }
}
