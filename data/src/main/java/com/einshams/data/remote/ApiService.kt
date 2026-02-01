package com.einshams.data.remote

import com.einshams.domain.entity.CategoryResponse
import retrofit2.http.GET

interface ApiService
{
    // get meals
    @GET("categories.php")
    suspend fun getMeals(): CategoryResponse
}