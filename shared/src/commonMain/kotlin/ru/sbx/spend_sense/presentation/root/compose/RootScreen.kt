package ru.sbx.spend_sense.presentation.root.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import ru.sbx.spend_sense.di.getKoinInstance
import ru.sbx.spend_sense.presentation.categories.CategoriesScreen
import ru.sbx.spend_sense.presentation.common.ui.theme.AppTheme
import ru.sbx.spend_sense.presentation.common.ui.theme.AppThemeProvider
import ru.sbx.spend_sense.presentation.events.EventsScreen
import ru.sbx.spend_sense.presentation.root.RootViewModel
import ru.sbx.spend_sense.presentation.root.model.AppTab
import ru.sbx.spend_sense.presentation.settings.compose.SettingsScreen

@Composable
fun RootScreen() {
    val viewModel = getKoinInstance<RootViewModel>()
    val state by viewModel.state.collectAsState()

    AppTheme(
        themeIsDark = state.isDarkTheme,
        appPrefs = state.appPrefs
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppThemeProvider.colors.background)
        ) {
            RootNavigation(state.selectedTab)
            RootBottomBar(state.selectedTab) {
                viewModel.onTabClicked(it)
            }
        }
    }
}

@Composable
fun BoxScope.RootNavigation(selectedTab: AppTab) {
    when (selectedTab) {
        AppTab.Categories -> CategoriesScreen(getKoinInstance())
        AppTab.Events -> EventsScreen(getKoinInstance())
        AppTab.Settings -> SettingsScreen(getKoinInstance())
    }
}