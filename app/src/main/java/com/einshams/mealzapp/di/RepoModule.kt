package com.einshams.mealzapp.di

import com.einshams.data.remote.ApiService
import com.einshams.data.repo_imp.MealsRepoImp
import com.einshams.domain.repo.MealsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule
{
    // Provide repository-related dependencies here (e.g., MealRepository)

    @Provides
    fun provideMealsRepo(apiService: ApiService): MealsRepo{
        return MealsRepoImp(apiService)
    }
}