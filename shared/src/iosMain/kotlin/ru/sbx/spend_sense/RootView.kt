package ru.sbx.spend_sense

import androidx.compose.ui.window.ComposeUIViewController
import ru.sbx.spend_sense.di.getKoinInstance
import ru.sbx.spend_sense.presentation.root.RootViewModel
import ru.sbx.spend_sense.presentation.root.compose.RootScreen

fun MainViewController() = ComposeUIViewController { RootScreen(RootViewModel(getKoinInstance())) }