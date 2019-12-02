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

package com.vmadalin.core.network.services

import com.vmadalin.core.network.responses.BaseResponse
import com.vmadalin.core.network.responses.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Representation interface of the Marvel API endpoints.
 */
interface MarvelService {

    /**
     * Fetches a single character resource. It is the canonical URI for any character resource
     * provided by the API.
     *
     * @param id A single character id.
     * @param apiKey A unique identifier used to authenticate all calling to an API.
     * @param hash A md5 digest of the ts parameter, private API key and public.
     * @param timestamp A digital current record of the time.
     * @return Response for single character resource.
     */
    @GET("/v1/public/characters/{id}")
    suspend fun getCharacter(
        @Path("id") id: Long,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") timestamp: String
    ): BaseResponse<CharacterResponse>

    /**
     * Fetches lists of comic characters with optional filters.
     *
     * @param apiKey A unique identifier used to authenticate all calling to an API.
     * @param hash A md5 digest of the ts parameter, private API key and public.
     * @param timestamp A digital current record of the time.
     * @param offset Skip the specified number of resources in the result set.
     * @param limit Limit the result set to the specified number of resources.
     * @return Response for comic characters resource.
     */
    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") timestamp: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): BaseResponse<CharacterResponse>
}
