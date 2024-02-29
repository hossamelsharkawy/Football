package com.hossam.football.features.competition.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hossam.football.features.competition.domain.GetCompetitionListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CompetitionViewModel @Inject constructor(
    private val getCompetitionListUseCase: GetCompetitionListUseCase,
) : ViewModel() {


    private val _uiState = MutableStateFlow(UIState())

    val uiState: StateFlow<UIState> = _uiState
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, e ->
        update {
            it.copy(
                isLoading = false,
                error = e.toString(),
            )
        }

    }

    init {
        getList()
    }

    private fun update(function: (UIState) -> UIState) = _uiState.update(function)


    fun onEvent(uiEvent: UIEvent) {
        when (uiEvent) {
            UIEvent.Refresh -> getList()
        }

    }


    private fun getList() {

        viewModelScope.launch(coroutineExceptionHandler) {
            val list = getCompetitionListUseCase()
            update {
                it.copy(
                    isLoading = false,
                    error = null,
                    list = list
                )
            }


        }
    }


}