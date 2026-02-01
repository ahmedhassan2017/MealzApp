package com.einshams.domain.common

import java.io.IOException
import retrofit2.HttpException

object ErrorMapper {
    fun toMessage(t: Throwable): String {
        return when (t) {
            is IOException -> "No internet connection. Please check your network."
            is HttpException -> {
                val code = t.code()
                when (code) {
                    401 -> "Unauthorized. Please login again."
                    404 -> "Not found."
                    in 500..599 -> "Server error. Please try again later."
                    else -> "Request failed (HTTP $code)."
                }
            }
            else -> (t.message ?: "Something went wrong.")
        }
    }
}
