package ru.sbx.spend_sense.presentation.events

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import ru.sbx.spend_sense.di.getKoinInstance
import ru.sbx.spend_sense.presentation.common.ui.calendar.compose.CalendarColors
import ru.sbx.spend_sense.presentation.common.ui.calendar.compose.DatePickerView
import ru.sbx.spend_sense.presentation.common.ui.theme.AppThemeProvider

@Composable
fun BoxScope.EventsScreen() {
    @Composable
    fun BoxScope.EventsScreen() {
        DatePickerView(
            viewModel = getKoinInstance(),
            colors = CalendarColors.default.copy(
                colorSurface = AppThemeProvider.colors.surface,
                colorOnSurface = AppThemeProvider.colors.onSurface,
                colorAccent = AppThemeProvider.colors.accent
            ),
            firstDayIsMonday = AppThemeProvider.appPrefs.firstDayIsMonday,
            labels = emptyList(),
            selectDayListener = { day -> }
        )
    }
}