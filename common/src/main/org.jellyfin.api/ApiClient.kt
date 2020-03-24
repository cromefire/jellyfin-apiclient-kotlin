package org.jellyfin.api

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import io.ktor.http.ContentType
import io.ktor.http.URLBuilder
import io.ktor.http.Url
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.jellyfin.api.models.AuthenticateRequest
import org.jellyfin.api.models.AuthenticateResponse
import org.jellyfin.api.models.User

private fun HttpRequestBuilder.addAuthToken(platform: Platform, token: String?) {
    val params = ArrayList<String>()
    params.add("Client=\"${platform.client}\"")
    params.add("Device=\"${platform.deviceName}\"")
    params.add("DeviceId=\"${platform.deviceId}\"")
    params.add("Version=\"${platform.clientVersion}\"")
    if (token != null) {
        params.add("Token=\"$token\"")
    }
    header("X-Emby-Authorization", "MediaBrowser ${params.joinToString(", ")}")
}

private fun HttpRequestBuilder.addPath(address: String, p: String) {
    this.url(URLBuilder(address).path(Url(address).encodedPath, p).build())
}

class ApiClient(
    //val appStorage: Any,
    val serverAddress: String,
    val platform: Platform,
    val token: String,
    val userId: String
) {
    suspend fun userInfo(): User {
        return client.get {
            auth()
            path("Users/$userId")
        }
    }

    // Helpers
    private fun HttpRequestBuilder.path(p: String) {
        this.addPath(serverAddress, p)
    }

    private fun HttpRequestBuilder.auth() {
        this.addAuthToken(platform, token)
    }

    companion object {
        val clientJson = Json(
            JsonConfiguration.Stable.copy(
                classDiscriminator = "Type",
                ignoreUnknownKeys = true
            )
        )

        private val client = HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(clientJson)
            }
        }

        suspend fun authenticateUserByName(
            platform: Platform,
            address: String,
            name: String,
            password: String
        ): AuthenticateResponse {
            return client.post(body = AuthenticateRequest(name, password)) {
                addAuthToken(platform, null)
                contentType(ContentType.Application.Json)
                addPath(address, "Users/AuthenticateByName")
            }
        }
    }
}
