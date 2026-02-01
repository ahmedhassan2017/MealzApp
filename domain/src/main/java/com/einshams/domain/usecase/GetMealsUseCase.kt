package com.einshams.domain.usecase

import com.einshams.domain.repo.MealsRepo



class GetMealsUseCase (private val mealsRepo: MealsRepo)
{
    suspend operator fun invoke() = mealsRepo.getMealsFromRemote()
}