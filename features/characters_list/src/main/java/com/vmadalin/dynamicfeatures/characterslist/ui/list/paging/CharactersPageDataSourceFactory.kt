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

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.vmadalin.dynamicfeatures.characterslist.ui.list.model.CharacterItem
import java.util.concurrent.Executors
import javax.inject.Inject
import kotlinx.coroutines.asCoroutineDispatcher

class CharactersPageDataSourceFactory
@Inject constructor(
    private val charactersPageDataSource: CharactersPageDataSource
) {

    @MainThread
    fun test(pageSize: Int): LiveData<PagedList<CharacterItem>> {
        val sourceLiveData = MutableLiveData<CharactersPageDataSource>()
        val sourceFactory = {
            sourceLiveData.postValue(charactersPageDataSource)
            charactersPageDataSource
        }

        // We use toLiveData Kotlin extension function here, you could also use LivePagedListBuilder
        val livePagedList = sourceFactory.toLiveData(
            pageSize = pageSize,
            // provide custom executor for network requests, otherwise it will default to
            // Arch Components' IO pool which is also used for disk access
            fetchDispatcher = Executors.newFixedThreadPool(5).asCoroutineDispatcher()
        )

        return livePagedList
    }
}
