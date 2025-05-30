package ru.sbx.spend_sense.presentation.categories.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import ru.sbx.spend_sense.extentions.now

data class Category(
    val id: String,
    val title: String,
    val description: String,
    val date: LocalDate,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object {
        val NONE = Category(
            id = "",
            title = "NONE_CATEGORY",
            description = "",
            date = LocalDate.now(),
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
        )
    }
}
