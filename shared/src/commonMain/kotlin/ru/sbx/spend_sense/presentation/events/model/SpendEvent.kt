package ru.sbx.spend_sense.presentation.events.model

import db.events.EventDb
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import ru.sbx.spend_sense.data.network.events.SpendEventApi
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
    val updatedAt: LocalDateTime,
    val note: String
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
            note = ""
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
    note = note
)

fun EventDb.toEntity() = SpendEvent(
    id = id,
    categoryId = categoryId,
    title = title.orEmpty(),
    date = date,
    cost = cost ?: 0.0,
    createdAt = createdAt,
    updatedAt = updatedAt,
    note = note.orEmpty()
)

fun SpendEvent.toApi() = SpendEventApi(
    id = id,
    categoryId = categoryId,
    title = title,
    cost = cost,
    date = date,
    createdAt = createdAt,
    updatedAt = updatedAt,
    note = note
)

fun SpendEventApi.toEntity() = SpendEvent(
    id = id.orEmpty(),
    categoryId = categoryId.orEmpty(),
    title = title.orEmpty(),
    cost = cost ?: 0.0,
    date = date ?: LocalDateTime.now().date,
    createdAt = createdAt ?: LocalDateTime.now(),
    updatedAt = updatedAt ?: LocalDateTime.now(),
    note = note.orEmpty()
)