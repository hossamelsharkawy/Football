package com.hossam.football

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hossam.football.features.competition.data.Area
import com.hossam.football.features.competition.data.CompetitionDto
import com.hossam.football.features.competition.data.CurrentSeason
import com.hossam.football.features.competition.ui.CompetitionDetailsScreen
import com.hossam.football.features.competition.ui.CompetitionListScreen
import com.hossam.football.features.competition.ui.UIState
import com.hossam.football.shared.fakeList
import com.hossam.football.ui.theme.FootballTheme

@Preview(showBackground = true)
@Composable
fun _GreetingPreview() {
    FootballTheme {
        CompetitionListScreen(
            uiState = UIState(
                fakeList {
                    CompetitionDto(
                        name = "Name $it",
                        currentSeason = CurrentSeason(startDate = "", endDate = ""),
                        area = Area(name = "Area Name")
                    )
                }


            ),
            onEvent = {},
            onNavToDetails = {},
            modifier = Modifier
        )
    }
}

@Preview
@Composable
fun _CompetitionDetailsScreen(
    competitionDto: CompetitionDto = CompetitionDto(
        name = "Name ",
        currentSeason = CurrentSeason(startDate = "", endDate = ""),
        area = Area(name = "Area Name")
    ),
)  {
    FootballTheme {
        CompetitionDetailsScreen(competitionDto)
    }
}