package ru.sbx.spend_sense.presentation.categories.model

import db.categories.CategoryDb
import kotlinx.datetime.LocalDateTime
import ru.sbx.spend_sense.data.network.categories.CategoryApi
import ru.sbx.spend_sense.extentions.now

data class Category(
    val id: String,
    val title: String,
    val description: String,
    val colorHex: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object {
        val NONE = Category(
            id = "",
            title = "NONE_CATEGORY",
            description = "",
            colorHex = "",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
        )
    }
}

fun CategoryDb.toEntity() = Category(
    id = id,
    title = title.orEmpty(),
    description = description.orEmpty(),
    colorHex = colorHex,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun Category.toDb() = CategoryDb(
    id = id,
    title = title,
    description = description,
    colorHex = colorHex,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun Category.toApi() = CategoryApi(
    id = id,
    title = title,
    description = description,
    colorHex = colorHex,
    createdAt = createdAt,
    updatedAt = updatedAt,
)

fun CategoryApi.toEntity() = Category(
    id = id.orEmpty(),
    title = title.orEmpty(),
    description = description.orEmpty(),
    colorHex = colorHex.orEmpty(),
    createdAt = createdAt ?: LocalDateTime.now(),
    updatedAt = updatedAt ?: LocalDateTime.now()
)
