package ru.sbx.spend_sense.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import ru.sbx.spend_sense.data.network.auth.register.RegisterRequest
import ru.sbx.spend_sense.data.network.auth.signin.SignInRequest
import ru.sbx.spend_sense.data.network.categories.CategoryApi
import ru.sbx.spend_sense.data.network.events.SpendEventApi
import ru.sbx.spend_sense.data.storage.SettingsManager

class AppApi(
    private val httpClient: HttpClient,
    private val settings: SettingsManager
) {

    suspend fun register(req: RegisterRequest) =
        httpClient.post("${settings.serverUrl}/api/auth/local/register") {
            contentType(ContentType.Application.Json)
            setBody(req)
        }

    suspend fun signIn(req: SignInRequest) =
        httpClient.post("${settings.serverUrl}/api/auth/local") {
            contentType(ContentType.Application.Json)
            setBody(req)
        }

    suspend fun syncCategories(categories: List<CategoryApi>) =
        httpClient.post("${settings.serverUrl}/api/categories/sync") {
            contentType(ContentType.Application.Json)
            bearerAuth(settings.token)
            setBody(categories)
        }

    suspend fun syncEvents(events: List<SpendEventApi>) =
        httpClient.post("${settings.serverUrl}/api/spend-events/sync") {
            contentType(ContentType.Application.Json)
            bearerAuth(settings.token)
            setBody(events)
        }
}