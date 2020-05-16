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

import io.ktor.client.HttpClientConfig
import io.ktor.client.features.defaultRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.http.URLBuilder
import io.ktor.http.Url
import io.ktor.http.takeFrom


internal fun HttpRequestBuilder.addAuthToken(platform: Platform, token: String?) {
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


internal fun HttpClientConfig<*>.configureDefault(basePath: String, block: (HttpRequestBuilder.() -> Unit)? = null) {
    val base = Url(basePath)

    defaultRequest {
        url.takeFrom(URLBuilder().takeFrom(base).apply {
            encodedPath += url.encodedPath
        })
        header("Accept", "application/json;Profile=\"CamelCase\"")
        block?.invoke(this)
    }
}


internal fun HttpRequestBuilder.path(p: String) {
    url {
        path(p)
    }
}
