package com.hossam.football.features.competition.data

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hossam.football.features.competition.domain.CompetitionsLocalDataSource
import javax.inject.Inject

class CompetitionsLocalDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : CompetitionsLocalDataSource {

    private val sharedPreferencesKey = "competitions_data"

    override suspend fun getCompetitions(): List<CompetitionDto>? {

        val competitionsJson = sharedPreferences.getString(sharedPreferencesKey, null)
        return competitionsJson?.let {
            val type = object : TypeToken<List<CompetitionDto>>() {}.type
            Gson().fromJson(it, type)
        }
    }

    override suspend fun saveCompetitions(competitions: List<CompetitionDto>) {
        sharedPreferences.edit().apply {
            putString(sharedPreferencesKey, Gson().toJson(competitions))
            apply()
        }
    }

}