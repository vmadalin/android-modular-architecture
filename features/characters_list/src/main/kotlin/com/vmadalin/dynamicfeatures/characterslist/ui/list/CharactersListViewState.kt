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

import com.vmadalin.core.ui.base.BaseViewState

sealed class CharactersListViewState: BaseViewState {

    object Loading : CharactersListViewState()
    object AddedLoading : CharactersListViewState()
    object Empty : CharactersListViewState()
    object Error : CharactersListViewState()
    object Listed : CharactersListViewState()

    fun isLoading() = this is Loading
    fun isAddedLoading() = this is AddedLoading
    fun isEmpty() = this is Empty
    fun isError() = this is Error
    fun isListed() = this is Listed

}
