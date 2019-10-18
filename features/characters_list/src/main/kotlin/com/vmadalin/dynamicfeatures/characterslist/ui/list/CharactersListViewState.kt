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

import androidx.paging.PagedList
import com.vmadalin.dynamicfeatures.characterslist.ui.list.model.CharacterItem

sealed class CharactersListViewState {

    object Loading : CharactersListViewState()
    object Empty : CharactersListViewState()
    data class Error(val throwable: Throwable) : CharactersListViewState()
    data class Listed(val data: PagedList<CharacterItem>?) : CharactersListViewState()

    fun isLoading() = this is Loading
    fun isEmpty() = this is Empty
    fun isError() = this is Error
    fun isListed() = this is Listed

    fun data(): PagedList<CharacterItem>? = if (this is Listed) this.data else null
}
