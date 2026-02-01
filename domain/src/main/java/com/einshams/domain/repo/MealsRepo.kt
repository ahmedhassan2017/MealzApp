package com.einshams.domain.repo

import com.einshams.domain.entity.CategoryResponse

interface MealsRepo
{
   suspend fun getMealsFromRemote(): CategoryResponse
}