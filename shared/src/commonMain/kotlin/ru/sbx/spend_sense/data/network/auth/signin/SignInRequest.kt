package ru.sbx.spend_sense.data.network.auth.signin

import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(
    val identifier: String,
    val password: String
)