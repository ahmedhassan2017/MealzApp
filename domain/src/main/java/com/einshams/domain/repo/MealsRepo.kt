package com.einshams.domain.repo

import com.einshams.domain.common.ResultWrapper
import com.einshams.domain.entity.Category
import com.einshams.domain.entity.CategoryResponse
import kotlinx.coroutines.flow.Flow

interface MealsRepo
{
   suspend fun getMealsFromRemote(): Flow<ResultWrapper<List<Category>>>
}