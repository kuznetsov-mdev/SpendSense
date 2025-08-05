package ru.sbx.spend_sense.presentation.settings.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.sbx.spend_sense.MR
import ru.sbx.spend_sense.presentation.common.ui.atoms.AppButton
import ru.sbx.spend_sense.presentation.common.ui.atoms.AppCard
import ru.sbx.spend_sense.presentation.common.ui.theme.AppThemeProvider

@Composable
fun SyncView(
    email: String,
    isLoading: Boolean,
    syncListener: () -> Unit,
    logoutListener: () -> Unit
) {
    AppCard {
        Box {
            Column {
                Text(
                    text = "${MR.string.auth_info} $email",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 20.sp
                )
                AppButton(
                    title = MR.string.sync,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    onClick = syncListener
                )

                AppButton(
                    title = MR.string.logout,
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    onClick = logoutListener
                )
            }

            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize()
                        .background(AppThemeProvider.colors.background.copy(0.5f))
                        .clickable { }

                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = AppThemeProvider.colors.accent
                    )
                }
            }
        }
    }
}