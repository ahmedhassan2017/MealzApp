package com.einshams.data.repo_imp


import com.einshams.data.mapper.toDomainList
import com.einshams.data.remote.ApiService
import com.einshams.domain.common.ErrorMapper
import com.einshams.domain.common.ResultWrapper
import com.einshams.domain.entity.Category
import com.einshams.domain.repo.MealsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MealsRepoImp(
        private val apiService: ApiService
) : MealsRepo {

    override suspend fun getMealsFromRemote(): Flow<ResultWrapper<List<Category>>> = flow {
        emit(ResultWrapper.Loading)
        try {
            val response = apiService.getMeals()
            val data: List<Category> = response.categories?.toDomainList().orEmpty()
            emit(ResultWrapper.Success(data))
        } catch (t: Throwable) {
            emit(ResultWrapper.Error(ErrorMapper.toMessage(t), t))
        }
    }


}
