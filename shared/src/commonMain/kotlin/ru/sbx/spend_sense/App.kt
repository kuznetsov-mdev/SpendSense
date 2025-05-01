package ru.sbx.spend_sense

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SayHelloFromCommon() {
    Box(modifier = Modifier.size(200.dp)) {
        Text(modifier = Modifier.align(Alignment.Center),
            text = "Hello from compose Common"
        )
    }
}