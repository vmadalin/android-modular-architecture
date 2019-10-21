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

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.vmadalin.core.network.repositiories.MarvelRepository
import com.vmadalin.dynamicfeatures.characterslist.ui.list.model.CharacterItem
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

/**
 * Data source factory which also provides a way to observe the last created data source.
 * This allows us to channel its network request status etc back to the UI.
 */
class CharactersPageDataSourceFactory
@Inject constructor(
    private val repository: MarvelRepository,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, CharacterItem>() {

    val sourceLiveData = MutableLiveData<CharactersPageDataSource>()

    override fun create(): DataSource<Int, CharacterItem> {
        val dataSource = CharactersPageDataSource(repository, scope)
        sourceLiveData.postValue(dataSource)
        return dataSource
    }

    fun refresh() {
        sourceLiveData.value?.invalidate()
    }

    fun retry() {
        sourceLiveData.value?.retry()
    }
}
