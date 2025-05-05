package ru.sbx.spend_sense.presentation.root

import androidx.compose.runtime.Composable
import ru.sbx.spend_sense.presentation.settings.SettingsScreen
import ru.sbx.spend_sense.presentation.settings.SettingsViewModel

@Composable
fun RootScreen() {
    SettingsScreen(SettingsViewModel())
}