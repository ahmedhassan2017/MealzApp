package com.einshams.mealzapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.einshams.domain.entity.Category
import com.einshams.domain.entity.CategoryResponse
import com.einshams.domain.usecase.GetMealsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(private val getMealsUseCase: GetMealsUseCase) : ViewModel()
{
    private val _categories : MutableStateFlow<List<Category>?> = MutableStateFlow(null)
    val categories : StateFlow<List<Category>?> = _categories
    fun fetchMeals()
    {
        viewModelScope.launch {
            try
            {
                val meals = getMealsUseCase()
                _categories.value = meals
            }
            catch (e: Exception)
            {
                // Handle error
                Log.e("MealsViewModel", "Error fetching meals: ${e.message}")
            }
        }

    }
}