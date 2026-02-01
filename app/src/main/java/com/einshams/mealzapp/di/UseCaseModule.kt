package com.einshams.mealzapp.di
import com.einshams.domain.repo.MealsRepo
import com.einshams.domain.usecase.GetMealsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule
{
    // Provide use case-related dependencies here (e.g., UseCase classes)

    @Provides
    fun provideMealsUseCase(mealsRepo: MealsRepo): GetMealsUseCase
    {
        return GetMealsUseCase(mealsRepo)
    }
}