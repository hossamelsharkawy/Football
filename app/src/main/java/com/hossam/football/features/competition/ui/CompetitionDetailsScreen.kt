package com.hossam.football.features.competition.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hossam.football.features.competition.data.CompetitionDto
import com.hossam.football.ui.components.MyImageLoading
import com.hossam.football.ui.components.MySpacer
import com.hossam.football.ui.components.MySpacerSmall
import com.hossam.football.ui.components.TitleValue


@Composable
fun CompetitionDetailsScreen(
    competitionDto: CompetitionDto
) = with(competitionDto) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 20.dp),
        ) {
        MySpacerSmall()

        Box(Modifier.fillMaxWidth()) {
            MyImageLoading(
                emblem,
                Modifier
                    .align(Alignment.Center)
                    .size(80.dp),
                )
        }
        MySpacerSmall()
        Text(name, style = MaterialTheme.typography.titleLarge)
        MySpacer()
        MySpacer()

        Row(verticalAlignment = Alignment.CenterVertically) {

            Text(
                "Location",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )

            area?.flag?.let {
                MyImageLoading(
                    it,
                    Modifier.padding(5.dp)
                        .size(25.dp)

                )
            }
            Text(area?.name ?: "")

        }

        MySpacer()


        TitleValue("Type", type)
        TitleValue("Available Seasons", numberOfAvailableSeasons)
        with(currentSeason) {
            TitleValue("Start", startDate)
            TitleValue("End", endDate)
            TitleValue("Matchday", currentMatchday)
            TitleValue("Winner", winner?.name)
        }


    }

}


