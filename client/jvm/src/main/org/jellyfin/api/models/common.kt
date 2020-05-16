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

import kotlinx.serialization.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Serializable
actual data class CommonDate(val isoString: String) {
    constructor(date: LocalDate) : this(date.format(DateTimeFormatter.ISO_INSTANT))

    val java: LocalDate
        get() {
            return LocalDate.parse(isoString, DateTimeFormatter.ISO_INSTANT)
        }

    @Serializer(forClass = CommonDate::class)
    actual companion object : KSerializer<CommonDate> {
        actual override val descriptor: SerialDescriptor = PrimitiveDescriptor("CommonDate", PrimitiveKind.STRING)

        actual override fun serialize(encoder: Encoder, value: CommonDate) {
            encoder.encodeString(value.isoString)
        }

        actual override fun deserialize(decoder: Decoder): CommonDate {
            return CommonDate(decoder.decodeString())
        }
    }
}
