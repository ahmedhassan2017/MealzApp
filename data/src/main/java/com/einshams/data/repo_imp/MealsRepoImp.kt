package com.einshams.data.repo_imp

import com.einshams.data.mapper.toDomainList
import com.einshams.data.remote.ApiService
import com.einshams.domain.entity.Category
import com.einshams.domain.entity.CategoryResponse
import com.einshams.domain.repo.MealsRepo

class MealsRepoImp  (private val apiService: ApiService) : MealsRepo
{
    override suspend fun getMealsFromRemote(): List<Category> =
        apiService.getMeals()
            .categories?.toDomainList()
            .orEmpty()

}