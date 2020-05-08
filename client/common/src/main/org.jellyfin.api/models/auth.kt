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

package org.jellyfin.api.models

import kotlinx.serialization.Serializable

// General
@Serializable
data class User(
    val name: String,
    val serverId: String,
    val id: String,
    val hasPassword: Boolean,
    val lastLoginDate: String,
    val lastActivityDate: String,
    val configuration: UserConfiguration,
    val policy: UserPolicy
)

@Serializable
data class UserConfiguration(
    val enableNextEpisodeAutoPlay: Boolean
)

@Serializable
data class UserPolicy(
    val isAdministrator: Boolean,
    val isHidden: Boolean,
    val isDisabled: Boolean
)

// Requests
@Serializable
data class AuthenticateRequest(
    val username: String,
    val password: String
)

@Serializable
data class AuthenticateResponse(
    val serverId: String,
    val accessToken: String,
    val user: User
)
