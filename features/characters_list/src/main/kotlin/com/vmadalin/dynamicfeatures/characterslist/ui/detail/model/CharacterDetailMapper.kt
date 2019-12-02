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

package com.vmadalin.dynamicfeatures.characterslist.ui.detail.model

import com.vmadalin.core.mapper.Mapper
import com.vmadalin.core.network.responses.BaseResponse
import com.vmadalin.core.network.responses.CharacterResponse

private const val IMAGE_URL_FORMAT = "%s.%s"

/**
 * Helper class to transforms network response to visual model, catching the necessary data.
 *
 * @see Mapper
 */
class CharacterDetailMapper : Mapper<BaseResponse<CharacterResponse>, CharacterDetail> {

    /**
     * Transform network response to [CharacterDetail].
     *
     * @param from Network characters response.
     * @return List of parsed characters items.
     * @throws NoSuchElementException If the response results are empty.
     */
    @Throws(NoSuchElementException::class)
    override suspend fun map(from: BaseResponse<CharacterResponse>): CharacterDetail {
        val characterResponse = from.data.results.first()
        return CharacterDetail(
            id = characterResponse.id,
            name = characterResponse.name,
            description = characterResponse.description,
            imageUrl = IMAGE_URL_FORMAT.format(
                characterResponse.thumbnail.path.replace("http", "https"),
                characterResponse.thumbnail.extension
            )
        )
    }
}
