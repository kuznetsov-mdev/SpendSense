package ru.sbx.spend_sense.data.network

import kotlinx.serialization.Serializable

@Serializable
data class ApiErrorWrapper(
    val error: ApiError
)

@Serializable
data class ApiError(
    val message: String?,
    val name: String?,
    val status: Int?
)