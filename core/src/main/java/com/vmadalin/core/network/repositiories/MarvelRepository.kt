package com.vmadalin.core.network.repositiories

import com.vmadalin.core.BuildConfig
import com.vmadalin.core.extensions.toMD5
import com.vmadalin.core.network.responses.BaseResponse
import com.vmadalin.core.network.responses.CharacterResponse
import com.vmadalin.core.network.services.MarvelService

private const val API_PUBLIC_KEY = BuildConfig.MARVEL_KEY_PUBLIC
private const val API_PRIVATE_KEY = BuildConfig.MARVEL_KEY_PRIVATE
private const val HASH_FORMAT = "%s%s%s"

class MarvelRepository
constructor(private val service: MarvelService) {

    suspend fun getCharacter(id: Long): BaseResponse<CharacterResponse> {
        val timestamp = System.currentTimeMillis().toString()
        return service.getCharacter(
            id = id,
            apiKey = API_PUBLIC_KEY,
            hash = generateApiHash(timestamp),
            timestamp = timestamp)
    }

    suspend fun getCharacters(offset: Int, limit: Int): BaseResponse<CharacterResponse> {
        val timestamp = System.currentTimeMillis().toString()
        return service.getCharacters(
            apiKey = API_PUBLIC_KEY,
            hash = generateApiHash(timestamp),
            timestamp = timestamp,
            offset = offset,
            limit = limit)
    }

    // ============================================================================================
    //  Private methods
    // ============================================================================================

    private fun generateApiHash(timestamp: String) : String {
        return HASH_FORMAT.format(timestamp, API_PRIVATE_KEY, API_PUBLIC_KEY).toMD5()
    }

}
