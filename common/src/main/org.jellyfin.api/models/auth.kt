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
