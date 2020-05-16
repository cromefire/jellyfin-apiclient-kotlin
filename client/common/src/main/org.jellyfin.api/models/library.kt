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

@Serializable
internal data class ViewsResponse(
    @SerialName("Items")
    val items: List<BaseItem>
)

@Serializable
class BaseItem(
    @SerialName("Name")
    val name: String,
    @SerialName("OriginalTitle")
    val originalTitle: String? = null,
    @SerialName("ServerId")
    val serverId: String,
    @SerialName("Id")
    val id: String,
    @SerialName("Etag")
    val etag: String,
    @SerialName("SourceType")
    val sourceType: String? = null,
    @SerialName("PlaylistItemId")
    val playlistItemId: String? = null,
    @SerialName("DateCreated")
    val dateCreated: CommonDate,
    @SerialName("LastMediaAdded")
    val lastMediaAdded: CommonDate? = null,
    @SerialName("ExtraType")
    val extraType: Int? = null,
    @SerialName("AirsBeforeSeasonNumber")
    val airsBeforeSeasonNumber: Int? = null,
    @SerialName("AirsAfterSeasonNumber")
    val airsAfterSeasonNumber: Int? = null,
    @SerialName("AirsBeforeEpisodeNumber")
    val airsBeforeEpisodeNumber: Int? = null,
    @SerialName("AbsoluteEpisodeNumber")
    val absoluteEpisodeNumber: Int? = null,
    @SerialName("DisplaySpecialsWithSeasons")
    val displaySpecialsWithSeasons: Boolean? = null,
    @SerialName("CanDelete")
    val canDelete: Boolean? = null,
    @SerialName("CanDownload")
    val canDownload: Boolean? = null,
    @SerialName("HasSubtitles")
    val hasSubtitles: Boolean? = null,
    @SerialName("PreferredMetadataLanguage")
    val preferredMetadataLanguage: String? = null,
    @SerialName("PreferredMetadataCountryCode")
    val preferredMetadataCountryCode: String? = null,
    @SerialName("AwardSummary")
    val awardSummary: String? = null,
    @SerialName("ShareUrl")
    val shareUrl: String? = null,
    @SerialName("Metascore")
    val metascore: Float? = null,
    @SerialName("HasDynamicCategories")
    val hasDynamicCategories: Boolean? = null,
    @SerialName("AnimeSeriesIndex")
    val animeSeriesIndex: Int? = null,
    /**
     * Gets or sets a value indicating whether [supports synchronize].
     *
     * `null` if [supports synchronize] contains no value, `true` if [supports synchronize], `false` otherwise.
     */
    @SerialName("SupportsSync")
    val supportsSync: Boolean? = null
)
