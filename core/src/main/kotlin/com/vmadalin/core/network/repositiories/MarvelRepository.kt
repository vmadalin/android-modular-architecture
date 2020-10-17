/*
 * Copyright 2019 vmadalin.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vmadalin.core.network.repositiories

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import com.vmadalin.core.BuildConfig
import com.vmadalin.core.extensions.toMD5
import com.vmadalin.core.network.responses.BaseResponse
import com.vmadalin.core.network.responses.CharacterResponse
import com.vmadalin.core.network.services.MarvelService

private const val API_PUBLIC_KEY = BuildConfig.MARVEL_API_KEY_PUBLIC
private const val API_PRIVATE_KEY = BuildConfig.MARVEL_API_KEY_PRIVATE
private const val HASH_FORMAT = "%s%s%s"

/**
 * Repository module for handling marvel API network operations [MarvelService].
 */
class MarvelRepository(
    @VisibleForTesting(otherwise = PRIVATE)
    internal val service: MarvelService
) {

    /**
     * Get all info of Marvel character.
     *
     * @param id A single character id.
     * @return Response for single character resource.
     */
    suspend fun getCharacter(id: Long): BaseResponse<CharacterResponse> {
        val timestamp = System.currentTimeMillis().toString()
        return service.getCharacter(
            id = id,
            apiKey = API_PUBLIC_KEY,
            hash = generateApiHash(timestamp),
            timestamp = timestamp
        )
    }

    /**
     * Get all Marvel characters by paged.
     *
     * @param offset Skip the specified number of resources in the result set.
     * @param limit Limit the result set to the specified number of resources.
     * @return Response for comic characters resource.
     */
    suspend fun getCharacters(offset: Int, limit: Int): BaseResponse<CharacterResponse> {
        val timestamp = System.currentTimeMillis().toString()
        return service.getCharacters(
            apiKey = API_PUBLIC_KEY,
            hash = generateApiHash(timestamp),
            timestamp = timestamp,
            offset = offset,
            limit = limit
        )
    }

    // ============================================================================================
    //  Private generators methods
    // ============================================================================================

    /**
     * Generate a md5 digest of the timestamp parameter, private API key and public.
     *
     * @param timestamp A digital current record of the time.
     * @return The MD5 Hash
     */
    private fun generateApiHash(timestamp: String) =
        HASH_FORMAT.format(timestamp, API_PRIVATE_KEY, API_PUBLIC_KEY).toMD5()
}
