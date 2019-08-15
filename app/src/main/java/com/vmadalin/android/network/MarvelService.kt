package com.vmadalin.android.network

import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import com.vmadalin.android.network.responses.BaseResponse
import com.vmadalin.android.network.responses.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("/v1/public/characters")
    fun getCharacters(
        @Query("apikey") apiKey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String,
        @Nullable @Query("offset") offset: Int,
        @Nullable @Query("limit") limit: Int
    ): LiveData<BaseResponse<CharacterResponse>>
}
