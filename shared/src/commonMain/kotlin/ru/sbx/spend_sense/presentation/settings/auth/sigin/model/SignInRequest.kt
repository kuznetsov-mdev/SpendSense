package ru.sbx.spend_sense.presentation.settings.auth.sigin.model

import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(
    val identifier: String,
    val password: String
)