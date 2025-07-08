package ru.sbx.spend_sense.presentation.events.model

import ru.sbx.spend_sense.presentation.base.BaseViewState
import ru.sbx.spend_sense.presentation.categories.model.Category
import ru.sbx.spend_sense.presentation.common.ui.calendar.model.CalendarDay
import ru.sbx.spend_sense.presentation.common.ui.calendar.model.CalendarLabel

class EventsContract {
    data class State(
        val selectedDay: CalendarDay?,
        val events: List<SpendEvent>,
        val categories: List<Category>,
        val calendarLabels: List<CalendarLabel>
    ) : BaseViewState {
        val eventsByDay: List<SpendEventUi>
            get() = events.filter { it.date == selectedDay?.date }
                .map { spendEvent ->
                    spendEvent.toUi(
                        categories.firstOrNull { it.id == spendEvent.categoryId } ?: Category.NONE
                    )
                }

        companion object {
            val NONE = State(
                selectedDay = null,
                events = emptyList(),
                categories = emptyList(),
                calendarLabels = emptyList()
            )
        }
    }
}