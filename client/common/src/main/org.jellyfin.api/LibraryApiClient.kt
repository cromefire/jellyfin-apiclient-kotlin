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

import io.ktor.client.request.get
import org.jellyfin.api.models.BaseItem
import org.jellyfin.api.models.ViewsResponse

class LibraryApiClient(
    appStorage: Any?,
    serverAddress: String,
    platform: Platform,
    token: String,
    userId: String
) : BaseApiClient(
    appStorage,
    serverAddress,
    platform,
    token,
    userId
) {
    suspend fun views(): List<BaseItem> {
        return client.get<ViewsResponse> {
            url {
                path("users/$userId/views")
            }
        }.items
    }
}
