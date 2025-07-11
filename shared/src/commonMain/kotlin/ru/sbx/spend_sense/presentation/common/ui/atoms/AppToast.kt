package ru.sbx.spend_sense.presentation.common.ui.atoms

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.delay
import ru.sbx.spend_sense.presentation.common.ui.theme.AppThemeProvider

@Composable
fun BoxScope.AppToast(
    message: String?,
    backgroundColor: Color = AppThemeProvider.colors.background,
    hide: () -> Unit
) {
    AnimatedVisibility(
        visible = message != null,
        modifier = Modifier.align(Alignment.BottomCenter)
    ) {
        LaunchedEffect(Unit) {
            delay(3000)
            hide()
        }
        Text(
            text = message.orEmpty(),
            modifier = Modifier
                .padding(16.dp)
                .background(backgroundColor, RoundedCornerShape(16.dp))
                .clickable { hide() }
                .zIndex(1.0f),
            color = AppThemeProvider.colors.accent
        )
    }
}