package ru.sbx.spend_sense.presentation.events.creation.model

import kotlinx.datetime.LocalDate
import ru.sbx.spend_sense.extentions.now
import ru.sbx.spend_sense.presentation.base.BaseEvent
import ru.sbx.spend_sense.presentation.base.BaseViewState
import ru.sbx.spend_sense.presentation.categories.model.Category
import ru.sbx.spend_sense.presentation.events.model.SpendEvent

class CreationContract {
    data class State(
        val title: String,
        val category: Category,
        val date: LocalDate,
        val cost: Double
    ) : BaseViewState {
        companion object {
            val NONE = State(
                title = "",
                category = Category.NONE,
                date = LocalDate.now(),
                cost = 0.0
            )
        }
    }

    sealed interface Event : BaseEvent {
        data class Finish(val spendEvent: SpendEvent) : Event
    }
}