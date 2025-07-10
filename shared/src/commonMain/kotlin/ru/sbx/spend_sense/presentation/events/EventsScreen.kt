package ru.sbx.spend_sense.presentation.events

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex
import kotlinx.coroutines.launch
import ru.sbx.spend_sense.di.getKoinInstance
import ru.sbx.spend_sense.presentation.common.ui.atoms.FAB
import ru.sbx.spend_sense.presentation.common.ui.atoms.RootBox
import ru.sbx.spend_sense.presentation.common.ui.calendar.compose.CalendarColors
import ru.sbx.spend_sense.presentation.common.ui.calendar.compose.DatePickerView
import ru.sbx.spend_sense.presentation.common.ui.theme.AppThemeProvider
import ru.sbx.spend_sense.presentation.events.creation.compose.EventCreationView
import ru.sbx.spend_sense.presentation.events.list.compose.EventsViewModel
import ru.sbx.spend_sense.presentation.events.list.compose.SpendEventItem


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BoxScope.EventsScreen(
    viewModel: EventsViewModel
) {
    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden, skipHalfExpanded = true)
    val scope = rememberCoroutineScope()
    val state by viewModel.state.collectAsState()

    ModalBottomSheetLayout(
        sheetContent = {
            EventCreationView(
                isExpand = sheetState.currentValue == ModalBottomSheetValue.Expanded,
                selectedDay = state.selectedDay,
                viewModel = getKoinInstance()
            ) { newEvent ->
                viewModel.createEvent(newEvent)
                scope.launch { sheetState.hide() }
            }
        },
        sheetState = sheetState,
        sheetBackgroundColor = Color.Transparent,
        modifier = Modifier.zIndex(1f)
    ) {
        RootBox {
            Column {
                DatePickerView(
                    viewModel = getKoinInstance(),
                    colors = CalendarColors.default.copy(
                        colorSurface = AppThemeProvider.colors.surface,
                        colorOnSurface = AppThemeProvider.colors.onSurface,
                        colorAccent = AppThemeProvider.colors.accent
                    ),
                    firstDayIsMonday = AppThemeProvider.appPrefs.firstDayIsMonday,
                    labels = state.calendarLabels,
                    selectDayListener = viewModel::selectDay
                )

                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(state.eventsByDay) { eventUi ->
                        SpendEventItem(eventUi)
                    }
                }
            }

            FAB { scope.launch { sheetState.show() } }
        }
    }


}