package ru.sbx.spend_sense.presentation.common.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * staticCompositionLocalOf -> recomposition will be apply for whole compose graph
 * localCompositionOf -> recomposition will be apply only for local component (which use this settings)
 **/
val LocalAppPrefs = staticCompositionLocalOf { AppPrefs() }
val LocalAppColors = staticCompositionLocalOf { darkPalette }

@Composable
fun AppTheme(
    themeIsDark: Boolean,
    appPrefs: AppPrefs,
    content: @Composable () -> Unit
) {
    val colors = if (themeIsDark) darkPalette else lightPalette

    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppPrefs provides appPrefs,
        content = content
    )
}

object AppThemeProvider {

    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColors.current

    val appPrefs: AppPrefs
        @Composable
        @ReadOnlyComposable
        get() = LocalAppPrefs.current
}