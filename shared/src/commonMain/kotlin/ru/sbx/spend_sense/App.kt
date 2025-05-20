package ru.sbx.spend_sense

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.sbx.spend_sense.presentation.root.RootViewModel
import ru.sbx.spend_sense.presentation.root.compose.RootScreen

@Composable
fun SayHelloFromCommon() {
    Box(modifier = Modifier.fillMaxSize()) {
        RootScreen(RootViewModel())
    }
}