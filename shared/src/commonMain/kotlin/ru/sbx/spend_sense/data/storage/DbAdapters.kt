package ru.sbx.spend_sense.data.storage

import app.cash.sqldelight.ColumnAdapter
import db.categories.CategoryDb
import db.events.EventDb
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.toLocalDateTime

object DbAdapters {
    val categoryDbAdapter = CategoryDb.Adapter(
        LocalDateTimeAdapter, LocalDateTimeAdapter
    )

    val eventAdapter = EventDb.Adapter(
        LocalDateAdapter, LocalDateTimeAdapter, LocalDateTimeAdapter
    )
}

object LocalDateTimeAdapter : ColumnAdapter<LocalDateTime, String> {
    override fun decode(databaseValue: String): LocalDateTime = databaseValue.toLocalDateTime()

    override fun encode(value: LocalDateTime): String = value.toString()

}

object LocalDateAdapter : ColumnAdapter<LocalDate, String> {
    override fun decode(databaseValue: String): LocalDate = databaseValue.toLocalDate()

    override fun encode(value: LocalDate): String = value.toString()

}