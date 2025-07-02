package ru.sbx.spend_sense.presentation.categories.list.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.sbx.spend_sense.presentation.categories.model.Category
import ru.sbx.spend_sense.presentation.common.ui.atoms.ColorLabel
import ru.sbx.spend_sense.presentation.common.ui.theme.AppThemeProvider

@Composable
fun CategoryItem(category: Category, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .background(AppThemeProvider.colors.surface, RoundedCornerShape(8.dp))
            .padding(8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = category.title,
                fontSize = 22.sp,
                color = AppThemeProvider.colors.onSurface
            )

            if (category.description.isNotBlank()) {
                Text(
                    text = category.title,
                    fontSize = 18.sp,
                    color = AppThemeProvider.colors.onSurface.copy(alpha = 0.7f)
                )
                ColorLabel(category.colorHex)
            }
        }
    }
}