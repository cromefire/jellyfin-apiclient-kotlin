/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

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
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.jellyfin.api.models.AuthenticateRequest
import org.jellyfin.api.models.AuthenticateResponse

class PublicApiClient(
    val serverAddress: String,
    val platform: Platform
) {
    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(clientJson)
        }

        configure(serverAddress)
    }

    suspend fun authenticateUserByName(
        username: String,
        password: String
    ): AuthenticateResponse {
        return client.post(body = AuthenticateRequest(username, password)) {
            auth()
            contentType(ContentType.Application.Json)
            path("Users/AuthenticateByName")
        }
    }

    // Helpers
    private fun HttpRequestBuilder.auth() {
        this.addAuthToken(platform, null)
    }
}
