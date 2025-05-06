package ru.sbx.spend_sense.presentation.root

import androidx.compose.runtime.Composable
import ru.sbx.spend_sense.presentation.settings.SettingsViewModel
import ru.sbx.spend_sense.presentation.settings.compose.SettingsScreen

@Composable
fun RootScreen() {
    SettingsScreen(SettingsViewModel())
}