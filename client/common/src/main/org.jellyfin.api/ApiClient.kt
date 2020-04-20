/*
 * Copyright 2020
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package org.jellyfin.api

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import org.jellyfin.api.models.User


class ApiClient(
    //val appStorage: Any,
    val serverAddress: String,
    val platform: Platform,
    private val token: String,
    val userId: String
) {
    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(clientJson)
        }

        basePath(serverAddress)
    }

    val public = PublicApiClient(serverAddress, platform)

    suspend fun userInfo(): User {
        return client.get {
            auth()
            url {
                path("Users/$userId")
            }
        }
    }

    // Helpers
    private fun HttpRequestBuilder.auth() {
        this.addAuthToken(platform, token)
    }
}
