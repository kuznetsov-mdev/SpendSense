package ru.sbx.spend_sense.presentation.events.model

import db.events.EventDb
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import ru.sbx.spend_sense.extentions.now
import ru.sbx.spend_sense.presentation.categories.model.Category
import ru.sbx.spend_sense.presentation.common.ui.calendar.model.CalendarLabel

data class SpendEvent(
    val id: String,
    val categoryId: String,
    val title: String,
    val date: LocalDate,
    val cost: Double,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object {
        val NONE = SpendEvent(
            id = "",
            categoryId = "",
            title = "",
            cost = 0.0,
            date = LocalDate.now(),
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
        )
    }
}

fun SpendEvent.toUi(category: Category) = SpendEventUi(
    id = categoryId,
    category = category,
    title = title,
    cost = cost
)

fun SpendEvent.toCalendarLabel(category: Category) = CalendarLabel(
    id = id,
    colorHex = category.colorHex,
    localDate = date
)

fun SpendEvent.toDb() = EventDb(
    id = id,
    categoryId = categoryId,
    title = title,
    date = date,
    cost = cost,
    createdAt = createdAt,
    updatedAt = updatedAt,
)

fun EventDb.toEntity() = SpendEvent(
    id = id,
    categoryId = categoryId,
    title = title.orEmpty(),
    date = date,
    cost = cost ?: 0.0,
    createdAt = createdAt,
    updatedAt = updatedAt,
)