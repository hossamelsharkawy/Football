package com.hossam.football.features.competition.ui

import com.hossam.football.features.competition.data.CompetitionDto

data class UIState(
    var list: List<CompetitionDto> = emptyList(),
    var isLoading: Boolean = true,
    var error: String? = null,
)


sealed class UIEvent {
    data object Refresh : UIEvent()

}