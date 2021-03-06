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

/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.jellyfin.api

import io.ktor.client.request.get
import org.jellyfin.api.models.User


class ApiClient(
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
    val public = PublicApiClient(serverAddress, platform)
    val library = LibraryApiClient(
        appStorage,
        serverAddress,
        platform,
        token,
        userId
    )

    suspend fun userInfo(): User {
        return client.get {
            url {
                path("Users/$userId")
            }
        }
    }
}
