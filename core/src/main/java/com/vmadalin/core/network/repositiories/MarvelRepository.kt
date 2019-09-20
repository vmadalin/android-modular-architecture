package com.vmadalin.core.network.repositiories

import androidx.lifecycle.LiveData
import com.vmadalin.core.BuildConfig
import com.vmadalin.core.extensions.toMD5
import com.vmadalin.core.network.responses.BaseResponse
import com.vmadalin.core.network.responses.CharacterResponse
import com.vmadalin.core.network.services.MarvelService
import javax.inject.Inject

class MarvelRepository
constructor(private val service: MarvelService) {

    suspend fun getCharacters(offset: Int, limit: Int): BaseResponse<CharacterResponse> {
        val timestamp = System.currentTimeMillis().toString()
        val hash = (timestamp + BuildConfig.MARVEL_KEY_PRIVATE + BuildConfig.MARVEL_KEY_PUBLIC).toMD5()
        return service.getCharacters(
            apiKey = BuildConfig.MARVEL_KEY_PUBLIC,
            hash = hash,
            timestamp = timestamp,
            offset = offset,
            limit = limit)
    }

}
