package ru.sbx.spend_sense.data

import kotlinx.coroutines.flow.flow
import ru.sbx.spend_sense.presentation.events.model.SpendEvent

class EventsRepository {
    fun getAllFlow() = flow { emit(SpendEvent.getStubs()) }

    fun create(spendEvent: SpendEvent) = Unit
}