package com.einshams.mealzapp.ui


import com.einshams.domain.entity.Category

data class MealsUiState(
        val isLoading: Boolean = false,
        val data: List<Category> = emptyList(),
        val errorMessage: String? = null
)
