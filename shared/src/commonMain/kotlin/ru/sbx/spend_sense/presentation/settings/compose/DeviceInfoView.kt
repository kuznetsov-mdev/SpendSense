package ru.sbx.spend_sense.presentation.settings.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.sbx.spend_sense.presentation.common.ui.atoms.AppCard
import ru.sbx.spend_sense.presentation.common.ui.theme.AppThemeProvider

@Composable
fun DeviceInfoView(info: String) {
    AppCard {
        Text(
            info, color = AppThemeProvider.colors.onSurface,
            modifier = Modifier.padding(16.dp)
        )
    }
}