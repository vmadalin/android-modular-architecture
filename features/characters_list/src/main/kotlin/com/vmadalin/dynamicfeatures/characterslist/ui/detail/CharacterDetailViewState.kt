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

package com.vmadalin.dynamicfeatures.characterslist.ui.detail

import com.vmadalin.commons.ui.base.BaseViewState

sealed class CharacterDetailViewState : BaseViewState {

    object Loading : CharacterDetailViewState()
    object Error : CharacterDetailViewState()
    object AddToFavorite : CharacterDetailViewState()
    object AddedToFavorite : CharacterDetailViewState()
    object AlreadyAddedToFavorite : CharacterDetailViewState()
    object Dismiss : CharacterDetailViewState()

    fun isLoading() = this is Loading
    fun isError() = this is Error
    fun isAddToFavorite() = this is AddToFavorite
    fun isAddedToFavorite() = this is AddedToFavorite
    fun isAlreadyAddedToFavorite() = this is AlreadyAddedToFavorite
    fun isDismiss() = this is Dismiss
}
