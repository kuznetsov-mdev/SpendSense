package ru.sbx.spend_sense.presentation.settings.auth.register.model

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val email: String,
    val password: String,
    val username: String
)