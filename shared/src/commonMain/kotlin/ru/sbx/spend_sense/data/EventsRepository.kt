package ru.sbx.spend_sense.data

import ru.sbx.spend_sense.data.storage.dao.SpendEventDao
import ru.sbx.spend_sense.presentation.events.model.SpendEvent

class EventsRepository(
    private val dao: SpendEventDao
) {
    fun getAllFlow() = dao.getAllFlow()

    suspend fun getAll() = dao.getAll()

    suspend fun insertAll(events: List<SpendEvent>) = dao.insertAll(events)

    suspend fun create(spendEvent: SpendEvent) = dao.insertAll(listOf(spendEvent))
}