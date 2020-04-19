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

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// General
@Serializable
data class User(
    @SerialName("Name")
    val name: String,
    @SerialName("ServerId")
    val serverId: String,
    @SerialName("Id")
    val id: String,
    @SerialName("HasPassword")
    val hasPassword: Boolean,
    @SerialName("LastLoginDate")
    val lastLoginDate: String,
    @SerialName("LastActivityDate")
    val lastActivityDate: String,
    @SerialName("Configuration")
    val configuration: UserConfiguration,
    @SerialName("Policy")
    val policy: UserPolicy
)

@Serializable
data class UserConfiguration(
    @SerialName("EnableNextEpisodeAutoPlay")
    val enableNextEpisodeAutoPlay: Boolean
)

@Serializable
data class UserPolicy(
    @SerialName("IsAdministrator")
    val isAdministrator: Boolean,
    @SerialName("IsHidden")
    val isHidden: Boolean,
    @SerialName("IsDisabled")
    val isDisabled: Boolean
)

// Requests
@Serializable
data class AuthenticateRequest(
    @SerialName("Username")
    val username: String,
    @SerialName("Pw")
    val password: String
)

@Serializable
data class AuthenticateResponse(
    @SerialName("ServerId")
    val serverId: String,
    @SerialName("AccessToken")
    val accessToken: String,
    @SerialName("User")
    val user: User
)
