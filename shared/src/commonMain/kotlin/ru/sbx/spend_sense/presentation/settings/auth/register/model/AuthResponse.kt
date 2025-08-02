package ru.sbx.spend_sense.presentation.settings.auth.register.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val jwt: String?
)
