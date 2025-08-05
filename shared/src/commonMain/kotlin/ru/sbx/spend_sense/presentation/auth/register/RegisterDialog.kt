package ru.sbx.spend_sense.presentation.auth.register

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.sbx.spend_sense.MR
import ru.sbx.spend_sense.presentation.common.ui.atoms.AppButton
import ru.sbx.spend_sense.presentation.common.ui.atoms.AppTextField
import ru.sbx.spend_sense.presentation.common.ui.atoms.AppToast
import ru.sbx.spend_sense.presentation.common.ui.theme.AppThemeProvider

@Composable
fun RegisterDialog(
    viewModel: RegisterViewModel,
    successListener: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    var showMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        viewModel.events.onEach { event ->
            when (event) {
                is RegisterContract.Event.Error -> showMessage = event.message
                RegisterContract.Event.Success -> successListener()
            }
        }.launchIn(this)
    }

    Box {
        Column(
            modifier = Modifier
                .background(AppThemeProvider.colors.surface, RoundedCornerShape(16.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppTextField(
                value = state.email,
                placeholder = MR.string.email,
                onValueChange = viewModel::changeEmail
            )

            AppTextField(
                value = state.password,
                placeholder = MR.string.password,
                onValueChange = viewModel::changePassword
            )

            AppTextField(
                value = state.confirmPassword,
                placeholder = MR.string.confirm_password,
                onValueChange = viewModel::changeConfirmPassword
            )

            AnimatedVisibility(
                visible = state.sendIsActive,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                AppButton(
                    title = MR.string.register,
                    onClick = viewModel::register
                )
            }
        }

        AppToast(showMessage) { showMessage = null }
    }
}