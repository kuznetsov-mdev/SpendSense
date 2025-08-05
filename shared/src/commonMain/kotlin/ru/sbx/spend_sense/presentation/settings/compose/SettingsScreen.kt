package ru.sbx.spend_sense.presentation.settings.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.sbx.spend_sense.MR
import ru.sbx.spend_sense.presentation.auth.AuthView
import ru.sbx.spend_sense.presentation.common.ui.theme.AppThemeProvider
import ru.sbx.spend_sense.presentation.settings.SettingsViewModel

@Composable
fun BoxScope.SettingsScreen(
    viewModel: SettingsViewModel
) {
    val state by viewModel.state.collectAsState()

    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Top) {

        if (state.isAuth) {

        } else {
            AuthView {
                //sync
            }
        }

        Row(
            modifier = Modifier
                .padding(16.dp)
                .background(AppThemeProvider.colors.surface, RoundedCornerShape(16.dp))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                MR.string.dark_theme, modifier = Modifier.weight(1f),
                color = AppThemeProvider.colors.onSurface
            )
            Switch(
                state.isDarkTheme,
                onCheckedChange = { viewModel.switchTheme(it) },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = AppThemeProvider.colors.accent,
                    uncheckedTrackColor = AppThemeProvider.colors.onSurface,
                    checkedTrackColor = AppThemeProvider.colors.accent.copy(0.5f)
                )
            )
        }

        DeviceInfoView(state.info)
    }
}