package com.einshams.data.model


import com.google.gson.annotations.SerializedName

data class CategoryResponseDto(
        @SerializedName("categories") val categories: List<CategoryDto>?
)
