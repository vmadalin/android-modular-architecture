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

package com.vmadalin.dynamicfeatures.characterslist.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.vmadalin.core.network.NetworkState
import com.vmadalin.dynamicfeatures.characterslist.ui.list.model.CharacterItem
import com.vmadalin.dynamicfeatures.characterslist.ui.list.paging.CharactersPageDataSourceFactory
import com.vmadalin.dynamicfeatures.characterslist.ui.list.paging.PAGE_MAX_ELEMENTS
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

class CharactersListViewModel
@Inject constructor(
    private val coroutineScope: CoroutineScope,
    private val dataSourceFactory: CharactersPageDataSourceFactory
) : ViewModel() {

    val data = LivePagedListBuilder(dataSourceFactory, PAGE_MAX_ELEMENTS).build()
    val networkState = Transformations.switchMap(dataSourceFactory.sourceLiveData) {
        it.networkState
    }
    private val _state = Transformations.map(networkState) {
        when (it) {
            is NetworkState.Success -> CharactersListViewState.Listed
            is NetworkState.Loading ->
                if (it.isAdditional) {
                    CharactersListViewState.AddedLoading
                } else {
                    CharactersListViewState.Loading
                }
            else -> CharactersListViewState.Error
        }
    }

    val state: LiveData<CharactersListViewState>
        get() = _state

    fun refreshLoadedCharactersList() {
        dataSourceFactory.refresh()
    }

    fun retryLoadCharactersList() {
        dataSourceFactory.retry()
    }


    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}
