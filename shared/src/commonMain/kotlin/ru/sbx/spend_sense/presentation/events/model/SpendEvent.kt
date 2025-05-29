package ru.sbx.spend_sense.presentation.events.model

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import ru.sbx.spend_sense.extentions.now

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

        fun getStubs() = List(20) { idx ->
            NONE.copy(
                id = idx.toString(),
                title = "event $idx",
                date = Clock.System.now()
                    .plus(idx, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
                    .toLocalDateTime(TimeZone.currentSystemDefault())
                    .date
            )
        }
    }
}
