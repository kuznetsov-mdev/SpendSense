package ru.sbx.spend_sense.presentation.common.ui.calendar.model

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.minus
import kotlinx.datetime.plus

data class CalendarMonth(
    val year: Int,
    val month: Month
) {
    private val localDate = LocalDate(year = year, month = month, dayOfMonth = 1)
    private var numberDays = 0
    private val firstDayOfMonth = LocalDate(localDate.year, month, 1).dayOfWeek

    init {
        var ld = localDate
        while (ld.month == month) {
            ld = ld.plus(1, DateTimeUnit.DAY)
        }
        numberDays = ld.minus(1, DateTimeUnit.DAY).dayOfMonth
    }

    private fun inflateCalendar(
        labels: List<CalendarLabel>,
        selectedDay: CalendarDay?,
        firstDayIsMonday: Boolean,
        weeks: MutableList<CalendarWeek>,
        accumulateWeek: MutableList<CalendarDay>
    ) {

        repeat(numberDays) { numberDay ->
            val dateOfDay = LocalDate(year, month, numberDay + 1)
            val dayOfWeek = dateOfDay.dayOfWeek

            val labelsByDay = labels.filter { it.localDate == dateOfDay }

            val calendarDay = CalendarDay(
                selectable = true,
                isSelected = selectedDay?.date == dateOfDay,
                date = dateOfDay,
                labels = labelsByDay,
                isStub = false
            )

            val isStartOfWeek =
                if (firstDayIsMonday) dayOfWeek == DayOfWeek.MONDAY else dayOfWeek == DayOfWeek.SUNDAY
            if (isStartOfWeek) {
                if (accumulateWeek.size != 0) {
                    weeks.add(CalendarWeek(accumulateWeek.toList()))
                    accumulateWeek.clear()
                    accumulateWeek.add(calendarDay)
                } else accumulateWeek.add(calendarDay)
            } else accumulateWeek.add(calendarDay)
        }
    }

    fun getWeeks(
        isFirstDayMonday: Boolean,
        selectedDay: CalendarDay?,
        labels: List<CalendarLabel> = emptyList()
    ): List<CalendarWeek> {
        val weeks = mutableListOf<CalendarWeek>()
        val accumulateWeek = mutableListOf<CalendarDay>()

        insertStartStubs(isFirstDayMonday, accumulateWeek)
        inflateCalendar(labels, selectedDay, isFirstDayMonday, weeks, accumulateWeek)
        insertEndStubs(accumulateWeek)

        weeks.add(CalendarWeek(accumulateWeek))
        return weeks

    }

    private fun insertStartStubs(
        isFirstDayMonday: Boolean,
        accumulateWeek: MutableList<CalendarDay>
    ) {
        val startStubsCount = if (isFirstDayMonday) firstDayOfMonth.ordinal
        else when (firstDayOfMonth) {
            DayOfWeek.SUNDAY -> 0
            else -> firstDayOfMonth.ordinal + 1
        }
        repeat(startStubsCount) { accumulateWeek.add((CalendarDay.NONE)) }
    }

    private fun insertEndStubs(accumulateWeek: MutableList<CalendarDay>) {
        val endStubsCount = DayOfWeek.values().size - accumulateWeek.size
        repeat(endStubsCount) {
            accumulateWeek.add(CalendarDay.NONE)
        }
    }

    companion object {
        fun fromDate(localDate: LocalDate): CalendarMonth {
            return CalendarMonth(localDate.year, localDate.month)
        }
    }
}
