package com.einshams.domain.repo

import com.einshams.domain.entity.Category
import com.einshams.domain.entity.CategoryResponse

interface MealsRepo
{
   suspend fun getMealsFromRemote(): List<Category>
}