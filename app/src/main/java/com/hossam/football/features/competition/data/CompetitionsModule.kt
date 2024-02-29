package com.hossam.football.features.competition.data

import android.content.SharedPreferences
import com.hossam.football.features.competition.domain.CompetitionsLocalDataSource
import com.hossam.football.features.competition.domain.CompetitionsRemoteDataSource
import com.hossam.football.features.competition.domain.CompetitionsRepository
import com.hossam.football.features.competition.domain.GetCompetitionListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit

@Module
@InstallIn(ActivityRetainedComponent::class)
object CompetitionsModule {

    @Provides
    fun provideCompetitionsRepository(
        remoteDataSource: CompetitionsRemoteDataSource,
        localDataSource: CompetitionsLocalDataSource,
    ): CompetitionsRepository =
        CompetitionsRepositoryImpl(localDataSource, remoteDataSource)

    @Provides
    fun provideCompetitionsRemoteDataSource(
        apiService: ApiService,
    ): CompetitionsRemoteDataSource =
        CompetitionsRemoteDataSourceImp(apiService)

    @Provides
    fun provideCompetitionsLocalDataSource(sh: SharedPreferences): CompetitionsLocalDataSource =
        CompetitionsLocalDataSourceImpl(sh)

    @Provides
    fun provideGetCompetitionListUseCase(competitionsRepository:CompetitionsRepository): GetCompetitionListUseCase =
        GetCompetitionListUseCase(competitionsRepository)


    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)



}