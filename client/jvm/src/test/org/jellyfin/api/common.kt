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

import kotlinx.coroutines.runBlocking
import org.jellyfin.api.models.AuthenticateResponse
import java.util.*

internal const val serverUrl = "https://demo.jellyfin.org/stable"

internal fun platform(): Platform {
    return Platform(
        "Kotlin api client",
        if (System.getenv("CI") == null) "Unit tests" else "Automated unit tests",
        UUID.randomUUID().toString(),
        System.getProperty("project.version") ?: "unknown"
    )
}

internal fun publicClient(): PublicApiClient {
    return PublicApiClient(
        serverUrl,
        platform()
    )
}

internal fun login(): AuthenticateResponse {
    val publicClient = publicClient()

    return runBlocking {
        publicClient.authenticateUserByName("demo", "")
    }
}

internal fun apiClient(): ApiClient {
    val credentials = login()

    return ApiClient(
        null,
        serverUrl,
        platform(),
        credentials.accessToken,
        credentials.user.id
    )
}
