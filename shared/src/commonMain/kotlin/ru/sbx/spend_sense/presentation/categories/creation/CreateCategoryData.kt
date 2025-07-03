package ru.sbx.spend_sense.presentation.categories.creation

import kotlinx.datetime.LocalDateTime
import ru.sbx.spend_sense.platform.randomUUID
import ru.sbx.spend_sense.presentation.categories.model.Category

data class CreateCategoryData(
    val title: String,
    val subtitle: String,
    val colorHex: String
)

fun CreateCategoryData.toCategory(dateTime: LocalDateTime) = Category(
    id = randomUUID(),
    title = title,
    description = subtitle,
    colorHex = colorHex,
    createdAt = dateTime,
    updatedAt = dateTime
)
