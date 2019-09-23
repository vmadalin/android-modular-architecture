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
import com.vmadalin.dynamicfeatures.characterslist.data.CharactersPageDataSourceFactory
import com.vmadalin.dynamicfeatures.characterslist.data.PAGE_INIT_ELEMENTS
import com.vmadalin.dynamicfeatures.characterslist.data.PAGE_MAX_ELEMENTS
import com.vmadalin.dynamicfeatures.characterslist.models.CharacterItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import javax.inject.Inject

class CharactersListViewModel
@Inject constructor(
    private val coroutineScope: CoroutineScope,
    private val dataSourceFactory: CharactersPageDataSourceFactory
) : ViewModel() {

    //private var _charactersList = MutableLiveData<PagedList<CharacterItem>?>()
    var charactersList: LiveData<PagedList<CharacterItem>>
        //get() = _charactersList

    init {
        charactersList = dataSourceFactory.test(PAGE_MAX_ELEMENTS)
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}
