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

package com.vmadalin.dynamicfeatures.characterslist.ui.list.paging

import androidx.paging.PageLoadType
import androidx.paging.PagedSource
import com.vmadalin.core.network.repositiories.MarvelRepository
import com.vmadalin.core.network.responses.BaseResponse
import com.vmadalin.core.network.responses.CharacterResponse
import com.vmadalin.dynamicfeatures.characterslist.ui.list.model.CharacterItem
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

const val PAGE_INIT_ELEMENTS = 0
const val PAGE_MAX_ELEMENTS = 50

class CharactersPageDataSource @Inject constructor(
    private val repository: MarvelRepository
) : PagedSource<Int, CharacterItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterItem> =
        when (params.loadType) {
            PageLoadType.REFRESH -> loadInitial(params)
            PageLoadType.START -> loadBefore()
            PageLoadType.END -> loadAfter(params)
        }

    private suspend fun loadInitial(params: LoadParams<Int>): LoadResult<Int, CharacterItem> {
        val response = repository.getCharacters(
            PAGE_INIT_ELEMENTS,
            PAGE_MAX_ELEMENTS
        )
        val data = getCharacterItems(response)
        return LoadResult(
            data = data,
            nextKey = response.data.offset + PAGE_MAX_ELEMENTS,
            prevKey = response.data.offset - PAGE_MAX_ELEMENTS
        )
    }

    private fun loadBefore(): LoadResult<Int, CharacterItem> {
        throw NotImplementedError()
    }

    private suspend fun loadAfter(params: LoadParams<Int>): LoadResult<Int, CharacterItem> {
        val response = repository.getCharacters(
            PAGE_INIT_ELEMENTS,
            PAGE_MAX_ELEMENTS
        )
        val data = getCharacterItems(response)
        return LoadResult(
            data = data,
            nextKey = response.data.offset + PAGE_MAX_ELEMENTS,
            prevKey = response.data.offset - PAGE_MAX_ELEMENTS
        )
    }

    private fun getCharacterItems(response: BaseResponse<CharacterResponse>): List<CharacterItem> {
        return response.data.results.map {
            CharacterItem(
                id = it.id,
                name = it.name,
                description = it.description,
                imageUrl = (it.thumbnail.path + "." + it.thumbnail.extension).replace(
                    "http",
                    "https"
                )
            )
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        Timber.e("An error happened: ${e.message}")
    }
}
