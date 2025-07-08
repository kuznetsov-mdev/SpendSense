package ru.sbx.spend_sense.presentation.events.list.compose

import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import ru.sbx.spend_sense.data.CategoriesRepository
import ru.sbx.spend_sense.data.EventsRepository
import ru.sbx.spend_sense.presentation.base.BaseViewModel
import ru.sbx.spend_sense.presentation.categories.model.Category
import ru.sbx.spend_sense.presentation.common.ui.calendar.model.CalendarDay
import ru.sbx.spend_sense.presentation.common.ui.calendar.model.CalendarLabel
import ru.sbx.spend_sense.presentation.events.model.EventsContract
import ru.sbx.spend_sense.presentation.events.model.SpendEvent
import ru.sbx.spend_sense.presentation.events.model.toCalendarLabel

class EventsViewModel(
    private val categoriesRepository: CategoriesRepository,
    private val eventsRepository: EventsRepository
) : BaseViewModel<EventsContract.State, Nothing>() {

    override fun initialState() = EventsContract.State.NONE

    init {
        activate()
    }

    fun selectDay(day: CalendarDay) = updateState { copy(selectedDay = day) }

    fun createEvent(newEvent: SpendEvent) {
        viewModelScope.launch { eventsRepository.create(newEvent) }
    }

    private fun activate() {
        combine(
            eventsRepository.getAllFlow(),
            categoriesRepository.getAllFlow()
        ) { events, categories ->
            val labels = mapEventsCategoriesToLabel(events, categories)
            updateState {
                copy(
                    events = events,
                    categories = categories,
                    calendarLabels = labels
                )
            }
        }.launchIn(viewModelScope)
    }

    private fun mapEventsCategoriesToLabel(
        events: List<SpendEvent>,
        categories: List<Category>
    ): List<CalendarLabel> {
        return events.map { event ->
            val category = categories.firstOrNull {
                it.id == event.categoryId
            } ?: Category.NONE
            event.toCalendarLabel(category)
        }
    }

}