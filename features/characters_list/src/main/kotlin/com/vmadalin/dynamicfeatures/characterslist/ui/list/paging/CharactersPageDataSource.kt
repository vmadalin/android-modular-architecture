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

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.vmadalin.core.network.NetworkState
import com.vmadalin.core.network.repositiories.MarvelRepository
import com.vmadalin.dynamicfeatures.characterslist.ui.list.model.CharacterItem
import com.vmadalin.dynamicfeatures.characterslist.ui.list.model.CharacterItemMapper
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

const val PAGE_INIT_ELEMENTS = 0
const val PAGE_MAX_ELEMENTS = 50

open class CharactersPageDataSource @Inject constructor(
    @VisibleForTesting(otherwise = PRIVATE)
    val repository: MarvelRepository,
    @VisibleForTesting(otherwise = PRIVATE)
    val scope: CoroutineScope,
    @VisibleForTesting(otherwise = PRIVATE)
    val mapper: CharacterItemMapper
) : PageKeyedDataSource<Int, CharacterItem>() {

    val networkState = MutableLiveData<NetworkState>()
    private var retry: (() -> Unit)? = null

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CharacterItem>
    ) {
        networkState.postValue(NetworkState.Loading())
        scope.launch(CoroutineExceptionHandler { _, throwable ->
            retry = {
                loadInitial(params, callback)
            }
            networkState.postValue(NetworkState.Error(throwable))
        }) {
            val response = repository.getCharacters(
                offset = PAGE_INIT_ELEMENTS,
                limit = PAGE_MAX_ELEMENTS
            )
            val data = mapper.map(response)
            callback.onResult(mapper.map(response), null, PAGE_MAX_ELEMENTS)
            networkState.postValue(NetworkState.Success(isEmptyResponse = data.isEmpty()))
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CharacterItem>
    ) {
        networkState.postValue(NetworkState.Loading(true))
        scope.launch(CoroutineExceptionHandler { _, throwable ->
            retry = {
                loadAfter(params, callback)
            }
            networkState.postValue(NetworkState.Error(throwable, true))
        }) {
            val response = repository.getCharacters(
                offset = params.key,
                limit = PAGE_MAX_ELEMENTS
            )
            val data = mapper.map(response)
            callback.onResult(data, params.key + PAGE_MAX_ELEMENTS)
            networkState.postValue(NetworkState.Success(true, data.isEmpty()))
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CharacterItem>
    ) {
        // Ignored, since we only ever append to our initial load
    }

    fun retry() {
        retry?.invoke()
    }
}
