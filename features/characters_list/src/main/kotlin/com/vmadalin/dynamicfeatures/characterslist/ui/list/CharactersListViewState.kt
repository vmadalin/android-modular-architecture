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

import com.vmadalin.commons.base.BaseViewState

sealed class CharactersListViewState : BaseViewState {

    object Refreshing : CharactersListViewState()
    object Loaded : CharactersListViewState()
    object Loading : CharactersListViewState()
    object AddLoading : CharactersListViewState()
    object Empty : CharactersListViewState()
    object Error : CharactersListViewState()
    object AddError : CharactersListViewState()
    object NoMoreElements : CharactersListViewState()

    fun isRefreshing() = this is Refreshing
    fun isLoaded() = this is Loaded
    fun isLoading() = this is Loading
    fun isAddLoading() = this is AddLoading
    fun isEmpty() = this is Empty
    fun isError() = this is Error
    fun isAddError() = this is AddError
    fun isNoMoreElements() = this is NoMoreElements
}
