package ru.sbx.spend_sense

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.sbx.spend_sense.info.DeviceInfoScreen

@Composable
fun SayHelloFromCommon() {
    Box(modifier = Modifier.size(200.dp)) {
        DeviceInfoScreen()
    }
}