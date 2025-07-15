package ru.sbx.spend_sense.data

import ru.sbx.spend_sense.data.model.dao.EventDao
import ru.sbx.spend_sense.presentation.events.model.SpendEvent

class EventsRepository(
    private val dao: EventDao
) {
    fun getAllFlow() = dao.getAllFlow()

    suspend fun create(spendEvent: SpendEvent) = dao.insertAll(listOf(spendEvent))
}