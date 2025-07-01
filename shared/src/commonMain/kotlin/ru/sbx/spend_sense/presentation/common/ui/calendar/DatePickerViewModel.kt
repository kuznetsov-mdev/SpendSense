package ru.sbx.spend_sense.presentation.common.ui.calendar

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import ru.sbx.spend_sense.extentions.now
import ru.sbx.spend_sense.presentation.base.BaseEvent
import ru.sbx.spend_sense.presentation.base.BaseViewModel
import ru.sbx.spend_sense.presentation.base.BaseViewState
import ru.sbx.spend_sense.presentation.common.ui.calendar.DatePickerViewModel.Event
import ru.sbx.spend_sense.presentation.common.ui.calendar.DatePickerViewModel.State
import ru.sbx.spend_sense.presentation.common.ui.calendar.extensions.next
import ru.sbx.spend_sense.presentation.common.ui.calendar.extensions.prev
import ru.sbx.spend_sense.presentation.common.ui.calendar.model.CalendarDay
import ru.sbx.spend_sense.presentation.common.ui.calendar.model.CalendarLabel
import ru.sbx.spend_sense.presentation.common.ui.calendar.model.CalendarMonth
import ru.sbx.spend_sense.presentation.common.ui.calendar.model.CalendarWeek

class DatePickerViewModel : BaseViewModel<State, Event>() {

    override fun initialState(): State = State.NONE

    fun selectDay(day: CalendarDay) {
        pushEvent(Event.SelectDay(day))
        updateState { copy(selectedDay = day) }
        updateWeeks()
    }

    fun prevMonth() {
        val prevMonth = state.value.calendarMonth.month.prev()
        var year = state.value.calendarMonth.year
        if (prevMonth == Month.DECEMBER) year -= 1
        updateMonthInState(year, prevMonth)
    }

    fun nextMonth() {
        val nextMonth = state.value.calendarMonth.month.next()
        var year = state.value.calendarMonth.year
        if (nextMonth == Month.JANUARY) year += 1
        updateMonthInState(year, nextMonth)
    }

    fun updateYear(year: Int) {
        if (year == state.value.calendarMonth.year) return
        val newLocalDate = LocalDate(year, state.value.calendarMonth.month, 1)
        val newCalendarMonth = CalendarMonth.fromDate(newLocalDate)
        updateState { copy(calendarMonth = newCalendarMonth) }
        updateWeeks()
    }


    fun updateLabels(labels: List<CalendarLabel>) {
        updateState { copy(labels = labels) }
        updateWeeks()
    }

    fun activate(firstDayIsMonday: Boolean) {
        updateState {
            copy(
                calendarMonth = CalendarMonth.fromDate(LocalDate.now()),
                firstDayIsMonday = firstDayIsMonday
            )
        }
        updateWeeks()
    }

    private fun updateMonthInState(year: Int, month: Month) {
        val calendarMonth = CalendarMonth(year, month)
        updateState { copy(calendarMonth = calendarMonth) }
        updateWeeks()
    }

    private fun updateWeeks() = viewModelScope.launch(Dispatchers.Default) {
        with(state.value) {
            val weeks = calendarMonth.getWeeks(firstDayIsMonday, selectedDay, labels)
            updateState { copy(weeks = weeks) }
        }
    }

    data class State(
        val weeks: List<CalendarWeek>,
        val selectedDay: CalendarDay?,
        val firstDayIsMonday: Boolean,
        val labels: List<CalendarLabel>,
        val calendarMonth: CalendarMonth,
    ) : BaseViewState {

        companion object {
            val NONE = State(
                weeks = CalendarWeek.PLACEHOLDER,
                selectedDay = null,
                firstDayIsMonday = false,
                labels = emptyList(),
                calendarMonth = CalendarMonth.fromDate(LocalDate.now())
            )
        }
    }

    sealed interface Event : BaseEvent {
        data class SelectDay(val day: CalendarDay) : Event
    }

}

