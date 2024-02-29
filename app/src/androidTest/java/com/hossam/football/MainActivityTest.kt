package com.hossam.football

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hossam.football.features.competition.data.CompetitionDto
import com.hossam.football.features.competition.domain.GetCompetitionListUseCase
import com.hossam.football.features.competition.ui.CompetitionDetailsScreen
import com.hossam.football.features.competition.ui.CompetitionListScreen
import com.hossam.football.features.competition.ui.CompetitionViewModel
import com.hossam.football.ui.theme.FootballTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@HiltAndroidTest
class MainActivityTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()


    @Inject
    lateinit var getCompetitionListUseCase: GetCompetitionListUseCase

    private lateinit var viewModel: CompetitionViewModel


    @Before
    fun init() {
        hiltRule.inject()
        viewModel = CompetitionViewModel(getCompetitionListUseCase)

    }


    @Test
    fun givenState_whenCompetitionListShown_thenListShown() {
        composeTestRule.setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            FootballTheme {
                CompetitionListScreen(
                    uiState = uiState,
                    modifier = Modifier,
                    onEvent = {},
                    onNavToDetails = {}
                )
            }
        }
        composeTestRule.onNodeWithText("Name 0").assertIsDisplayed()
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun givenState_whenCompetitionSelected_thenDetailsShown() {
        composeTestRule.setContent {

            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            FootballTheme {
                var currentCompetitionDto by remember { mutableStateOf<CompetitionDto?>(null) }

                if (currentCompetitionDto != null) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            currentCompetitionDto = null
                        },
                    ) {
                        currentCompetitionDto?.let { CompetitionDetailsScreen(it) }
                    }
                }

                CompetitionListScreen(
                    modifier = Modifier,
                    onEvent = viewModel::onEvent,
                    onNavToDetails = {
                        currentCompetitionDto = it
                    },
                    uiState = uiState
                )
            }
        }

        composeTestRule.onNodeWithText("Name 1").performClick()
        composeTestRule.onNodeWithText("Location").assertIsDisplayed()

    }

}








