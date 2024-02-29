package com.hossam.football

import android.content.Context
import android.content.SharedPreferences
import com.hossam.football.features.competition.data.ApiService
import com.hossam.football.features.competition.data.Area
import com.hossam.football.features.competition.data.CompetitionDto
import com.hossam.football.features.competition.data.CompetitionsLocalDataSourceImpl
import com.hossam.football.features.competition.data.CompetitionsModule
import com.hossam.football.features.competition.data.CompetitionsRemoteDataSourceImp
import com.hossam.football.features.competition.data.CompetitionsRepositoryImpl
import com.hossam.football.features.competition.data.CompetitionsResponse
import com.hossam.football.features.competition.data.CurrentSeason
import com.hossam.football.features.competition.domain.CompetitionsLocalDataSource
import com.hossam.football.features.competition.domain.CompetitionsRemoteDataSource
import com.hossam.football.features.competition.domain.CompetitionsRepository
import com.hossam.football.features.competition.domain.GetCompetitionListUseCase
import com.hossam.football.shared.SharedPreferencesModule
import com.hossam.football.shared.fakeList
import com.hossam.football.shared.getEncryptedSharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import javax.inject.Singleton


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CompetitionsModule::class, SharedPreferencesModule::class]
)

object TestCompetitionsModule {

    @Singleton
    @Provides
    fun provideEncryptedSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        getEncryptedSharedPreferences(context)

    @Provides
    fun provideCompetitionsRepository(
        remoteDataSource: CompetitionsRemoteDataSource,
        localDataSource: CompetitionsLocalDataSource,
    ): CompetitionsRepository = CompetitionsRepositoryImpl(localDataSource, remoteDataSource)

    @Singleton
    @Provides
    fun provideCompetitionsRemoteDataSource(
        apiService: ApiService,
    ): CompetitionsRemoteDataSource = CompetitionsRemoteDataSourceImp(apiService)

    @Singleton
    @Provides
    fun provideCompetitionsLocalDataSource(sh: SharedPreferences): CompetitionsLocalDataSource =
        CompetitionsLocalDataSourceImpl(sh)


    @Provides
    fun provideGetCompetitionListUseCase(competitionsRepository: CompetitionsRepository): GetCompetitionListUseCase =
        GetCompetitionListUseCase(competitionsRepository)

    @Singleton
    @Provides
    fun provideTestCompetitionsConfig(): TestCompetitionsConfig = object : TestCompetitionsConfig {
        override var success = true
    }

    @Singleton
    @Provides
    fun provideApiService(
        testCompetitionsConfig: TestCompetitionsConfig,
    ): ApiService = object : ApiService {
        override suspend fun getList() = if (testCompetitionsConfig.success) {
            Response.success(
                CompetitionsResponse(fakeList {
                    CompetitionDto(
                        name = "Name $it",
                        currentSeason = CurrentSeason(startDate = "", endDate = ""),
                        area = Area(name = "Area Name")
                    )
                }
                ))
        } else {
            Response.error(404, "".toResponseBody())
        }
    }

}