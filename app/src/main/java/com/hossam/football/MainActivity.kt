package com.hossam.football

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hossam.football.features.competition.data.CompetitionDto
import com.hossam.football.features.competition.ui.CompetitionDetailsScreen
import com.hossam.football.features.competition.ui.CompetitionListScreen
import com.hossam.football.features.competition.ui.CompetitionViewModel
import com.hossam.football.ui.theme.FootballTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )

        enableEdgeToEdge()
        setContent {
            FootballTheme {
                var currentCompetitionDto by remember { mutableStateOf<CompetitionDto?>(null) }

                Scaffold(
                    modifier = Modifier
                        .statusBarsPadding()
                        .fillMaxSize(),

                    ) { innerPadding ->
                    val viewModel = hiltViewModel<CompetitionViewModel>()

                    if (currentCompetitionDto != null) {
                        ModalBottomSheet(
                            onDismissRequest = {
                                currentCompetitionDto = null
                            },
                        ) {
                            // Sheet content
                            currentCompetitionDto?.let { CompetitionDetailsScreen(it) }
                        }
                    }
                    CompetitionListScreen(
                        modifier = Modifier.padding(innerPadding),
                        uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
                        onEvent = viewModel::onEvent,
                        onNavToDetails = {
                            currentCompetitionDto = it

                        }
                    )

                }

            }
        }
    }
}


