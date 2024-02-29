package com.hossam.football

import android.content.SharedPreferences
import com.hossam.football.features.competition.domain.CompetitionsLocalDataSource
import com.hossam.football.features.competition.domain.GetCompetitionListUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class CompetitionsDataTest {

    @get:Rule(order = 0)
    val dispatcherRule = ViewModelRule()

    @get:Rule(order = 1)
    var hiltRule = HiltAndroidRule(this)


    @Inject
    lateinit var getCompetitionListUseCase: GetCompetitionListUseCase

    @Inject
    lateinit var localDataSource: CompetitionsLocalDataSource

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var testCompetitionsConfig: TestCompetitionsConfig


    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun givenCompetitionList_whenFetchingList_thenListNotEmpty() = runTest {
        assert(getCompetitionListUseCase().isNotEmpty())
    }

    @Test
    fun givenCompetitionList_whenSavedInLocalDataSource_thenRetrievedListMatches() = runTest {
        clearSharedPreferences()
        val list = getCompetitionListUseCase()
        assert(list == localDataSource.getCompetitions())
    }

    @Test
    fun givenCompetitionList_whenErrorInRemote_thenRetrievedFromLocal() = runTest {
        // Clear SharedPreferences
        clearSharedPreferences()

        // Fetch list for the first time and save it locally
        val firstList = getCompetitionListUseCase()
        assert(firstList.isNotEmpty())

        // Simulate error when fetching list from remote
        testCompetitionsConfig.success = false

        // Fetch list again (now from local) after failure to fetch from remote
        val secondList = getCompetitionListUseCase()
        assert(secondList.isNotEmpty())
    }

    private fun clearSharedPreferences() {
        sharedPreferences.edit().clear().commit()
    }
}

