package ru.sbx.spend_sense.data

import ru.sbx.spend_sense.data.model.dao.SpendEventDao
import ru.sbx.spend_sense.presentation.events.model.SpendEvent

class EventsRepository(
    private val dao: SpendEventDao
) {
    fun getAllFlow() = dao.getAllFlow()

    suspend fun create(spendEvent: SpendEvent) = dao.insertAll(listOf(spendEvent))
}