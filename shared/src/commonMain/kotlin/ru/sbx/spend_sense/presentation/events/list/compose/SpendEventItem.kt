package ru.sbx.spend_sense.presentation.events.list.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.sbx.spend_sense.MR
import ru.sbx.spend_sense.extentions.fromHex
import ru.sbx.spend_sense.presentation.common.ui.atoms.ColorLabel
import ru.sbx.spend_sense.presentation.common.ui.theme.AppThemeProvider
import ru.sbx.spend_sense.presentation.events.model.SpendEventUi

@OptIn(ExperimentalStdlibApi::class)
@Composable
fun SpendEventItem(
    eventUi: SpendEventUi
) {
    val categoryColor = eventUi.category.colorHex.takeIf { it.isNotBlank() }?.let {
        Color.fromHex(it)
    } ?: AppThemeProvider.colors.accent

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                AppThemeProvider.colors.surface.copy(0.8f),
                RoundedCornerShape(8.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            if (eventUi.title.isNotBlank()) {
                Text(
                    text = eventUi.title,
                    modifier = Modifier.padding(bottom = 2.dp),
                    fontSize = 20.sp,
                    color = AppThemeProvider.colors.onSurface
                )
            }

            Text(
                text = eventUi.category.title.ifEmpty { MR.string.empty_category },
                modifier = Modifier.padding(bottom = 2.dp),
                fontSize = 16.sp,
                color = categoryColor
            )

            Text(
                text = eventUi.cost.toString(),
                fontSize = 16.sp,
                color = AppThemeProvider.colors.onSurface
            )
        }

        ColorLabel(categoryColor.toArgb().toHexString())
    }
}