package ru.sbx.spend_sense.presentation.events.creation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.sbx.spend_sense.MR
import ru.sbx.spend_sense.di.DatePickerFactoryQualifier
import ru.sbx.spend_sense.di.getKoinInstance
import ru.sbx.spend_sense.presentation.categories.list.compose.CategoriesListView
import ru.sbx.spend_sense.presentation.common.ui.atoms.AppButton
import ru.sbx.spend_sense.presentation.common.ui.atoms.AppTextField
import ru.sbx.spend_sense.presentation.common.ui.atoms.BottomModalContainer
import ru.sbx.spend_sense.presentation.common.ui.atoms.TextPairButton
import ru.sbx.spend_sense.presentation.common.ui.calendar.compose.CalendarColors
import ru.sbx.spend_sense.presentation.common.ui.calendar.compose.DatePickerView
import ru.sbx.spend_sense.presentation.common.ui.calendar.model.CalendarDay
import ru.sbx.spend_sense.presentation.common.ui.theme.AppThemeProvider
import ru.sbx.spend_sense.presentation.events.creation.EventCreationViewModel
import ru.sbx.spend_sense.presentation.events.creation.model.CreationContract
import ru.sbx.spend_sense.presentation.events.model.SpendEvent

@Composable
fun EventCreationView(
    isExpand: Boolean,
    selectedDay: CalendarDay?,
    viewModel: EventCreationViewModel,
    createListener: (SpendEvent) -> Unit
) {
    val state by viewModel.state.collectAsState()
    var showDateDialog by remember { mutableStateOf(false) }
    var showCategoriesDialog by remember { mutableStateOf(false) }

    LaunchedEffect(isExpand) {
        if (isExpand) {
            viewModel.selectDate(selectedDay?.date)
        } else {
            viewModel.resetState()
        }

        viewModel.events.onEach { event ->
            when (event) {
                is CreationContract.Event.Finish -> createListener(event.spendEvent)
            }
        }.launchIn(this)
    }

    BottomModalContainer {
        TextPairButton(
            title = MR.string.category,
            buttonTitle = state.category.title.ifEmpty { MR.string.empty_category },
            colorHex = state.category.colorHex.takeIf { it.isNotEmpty() }
        ) { showCategoriesDialog = true }

        TextPairButton(
            title = MR.string.date,
            buttonTitle = state.category.title.ifEmpty { MR.string.empty_category },
        ) { showDateDialog = true }

        AppTextField(
            value = state.title,
            placeholder = MR.string.spend_to,
            modifier = Modifier.fillMaxWidth(),
        ) { viewModel.changeTitle(it) }

        AppTextField(
            value = state.cost.toString(),
            placeholder = MR.string.cost,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        ) { viewModel.changeCost(it) }

        AppButton(MR.string.save) {
            viewModel.finish()
        }
    }

    if (showCategoriesDialog) {
        Dialog(onDismissRequest = { showCategoriesDialog = false }) {
            CategoriesListView(
                viewModel = getKoinInstance(),
                modifier = Modifier.background(
                    color = AppThemeProvider.colors.surface,
                    shape = RoundedCornerShape(16.dp)
                )
            ) { category ->
                viewModel.selectCategory(category = category)
                showCategoriesDialog = false
            }
        }
    }

    if (showDateDialog) {
        Dialog(onDismissRequest = { showDateDialog = false }) {
            DatePickerView(
                viewModel = getKoinInstance(DatePickerFactoryQualifier),
                colors = CalendarColors.default.copy(
                    colorAccent = AppThemeProvider.colors.accent,
                    colorSurface = AppThemeProvider.colors.surface,
                    colorOnSurface = AppThemeProvider.colors.onSurface
                )
            ) { day ->
                viewModel.selectDate(day.date)
                showDateDialog = false
            }
        }
    }
}