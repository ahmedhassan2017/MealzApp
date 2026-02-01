package com.einshams.domain.common

// A generic wrapper to handle loading, success, and error states
sealed class ResultWrapper<out T> {
    data object Loading : ResultWrapper<Nothing>()
    data class Success<T>(val data: T) : ResultWrapper<T>()
    data class Error(
            val message: String,
            val throwable: Throwable? = null
    ) : ResultWrapper<Nothing>()
}

