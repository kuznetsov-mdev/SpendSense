package ru.sbx.spend_sense.presentation.root.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.sbx.spend_sense.presentation.common.ui.AppTheme
import ru.sbx.spend_sense.presentation.root.RootViewModel
import ru.sbx.spend_sense.presentation.settings.SettingsViewModel
import ru.sbx.spend_sense.presentation.settings.compose.SettingsScreen

@Composable
fun RootScreen(viewModel: RootViewModel) {

    val state by viewModel.state.collectAsState()

    AppTheme(
        themeIsDark = state.isDarkTheme,
        appPrefs = state.appPrefs
    ) {
        SettingsScreen(SettingsViewModel())
    }
}