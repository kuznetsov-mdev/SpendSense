package ru.sbx.spend_sense.data.network.auth.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val email: String,
    val password: String,
    val username: String
)