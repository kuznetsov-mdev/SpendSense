package ru.sbx.spend_sense.presentation.common.ui.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.sbx.spend_sense.extentions.fromHex
import ru.sbx.spend_sense.presentation.common.ui.theme.AppThemeProvider

@Composable
fun ColorLabel(colorHex: String) {
    val color = runCatching { Color.fromHex(colorHex) }.getOrNull() ?: AppThemeProvider.colors.accent
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(color.copy(alpha = 0.8f), RoundedCornerShape(8.dp))
            .border(2.dp, color, RoundedCornerShape(8.dp))
    )
}