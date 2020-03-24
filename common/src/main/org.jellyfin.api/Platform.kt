package org.jellyfin.api

data class Platform(
    val client: String,
    val deviceName: String,
    val deviceId: String,
    val clientVersion: String
)
