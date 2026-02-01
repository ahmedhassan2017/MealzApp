package com.einshams.data.mapper


import com.einshams.data.model.CategoryDto
import com.einshams.domain.entity.Category

// Mapper from CategoryDto to Category domain model
fun CategoryDto.toDomain(): Category {
    return Category(
        idCategory = idCategory.orEmpty(),
        category = strCategory.orEmpty(),
        categoryDescription = strCategoryDescription.orEmpty(),
        categoryThumb = strCategoryThumb.orEmpty()
    )
}

// Mapper from List<CategoryDto> to List<Category> domain model
fun List<CategoryDto>.toDomainList(): List<Category> = map { it.toDomain() }
