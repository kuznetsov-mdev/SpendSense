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

@Composable
fun ColorLabel(colorHex: String) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(Color.fromHex(colorHex).copy(alpha = 0.8f), RoundedCornerShape(8.dp))
            .border(2.dp, Color.fromHex(colorHex), RoundedCornerShape(8.dp))
    )
}