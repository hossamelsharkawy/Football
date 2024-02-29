package com.hossam.football.features.competition.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hossam.football.features.competition.data.CompetitionDto
import com.hossam.football.ui.components.MyImageLoading

@Composable
fun CompetitionListScreen(
    modifier: Modifier,
    uiState: UIState,
    onEvent: (UIEvent) -> Unit,
    onNavToDetails: (CompetitionDto) -> Unit,
) {
    AnimatedVisibility(visible = uiState.isLoading) {
        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
    }

    LazyColumn(
        modifier,
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp)
    ) {

        uiState.error?.let {
            item {
                Text(
                    text = it,
                    modifier = Modifier
                        .clickable { onEvent(UIEvent.Refresh) })
            }
        }


        items(uiState.list) {
            Card(
                elevation = CardDefaults.cardElevation(2.dp),
                modifier = Modifier.padding(vertical = 10.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier =
                    Modifier
                        .clickable {
                            onNavToDetails.invoke(it)
                        }
                ) {

                    MyImageLoading(
                        it.emblem,
                        Modifier
                            .padding(10.dp )
                            .size(80.dp)
                    )

                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .weight(1f)
                    ) {

                        Text(
                            it.name ?: "",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                        )

                        Text(it.area?.name ?: "")

                    }

                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowDown,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(10.dp)

                    )

                }
            }

        }
    }

}