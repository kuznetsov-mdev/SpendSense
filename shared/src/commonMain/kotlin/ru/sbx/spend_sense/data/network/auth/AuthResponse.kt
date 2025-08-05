package ru.sbx.spend_sense.data.network.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val jwt: String?
)