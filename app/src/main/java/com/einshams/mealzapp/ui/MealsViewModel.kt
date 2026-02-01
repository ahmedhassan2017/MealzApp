package com.einshams.mealzapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.einshams.domain.common.ResultWrapper
import com.einshams.domain.usecase.GetMealsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
        private val getMealsUseCase: GetMealsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MealsUiState())
    val uiState: StateFlow<MealsUiState> = _uiState

    fun fetchMeals() {
        viewModelScope.launch {
            getMealsUseCase().collect { result ->
                when (result) {
                    is ResultWrapper.Loading -> {
                        _uiState.update { it.copy(isLoading = true, errorMessage = null) }
                    }
                    is ResultWrapper.Success -> {
                        _uiState.update { it.copy(isLoading = false, data = result.data, errorMessage = null) }
                    }
                    is ResultWrapper.Error -> {
                        _uiState.update { it.copy(isLoading = false, errorMessage = result.message) }
                    }
                }
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(errorMessage = null) }
    }
}