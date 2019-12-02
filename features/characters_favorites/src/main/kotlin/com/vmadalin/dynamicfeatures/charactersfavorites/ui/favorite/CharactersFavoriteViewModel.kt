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

package com.vmadalin.dynamicfeatures.charactersfavorites.ui.favorite

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vmadalin.core.database.characterfavorite.CharacterFavorite
import com.vmadalin.core.database.characterfavorite.CharacterFavoriteRepository
import javax.inject.Inject
import kotlinx.coroutines.launch

/**
 * View model responsible for preparing and managing the data for [CharactersFavoriteFragment].
 *
 * @see ViewModel
 */
class CharactersFavoriteViewModel @Inject constructor(
    @VisibleForTesting(otherwise = PRIVATE)
    val characterFavoriteRepository: CharacterFavoriteRepository
) : ViewModel() {

    val data = characterFavoriteRepository.getAllCharactersFavoriteLiveData()
    val state = Transformations.map(data) {
        if (it.isEmpty()) {
            CharactersFavoriteViewState.Empty
        } else {
            CharactersFavoriteViewState.Listed
        }
    }

    // ============================================================================================
    //  Public methods
    // ============================================================================================

    /**
     * Remove the selected favorite character from database in case if exist.
     *
     * @param character Favorite character.
     */
    fun removeFavoriteCharacter(character: CharacterFavorite) {
        viewModelScope.launch {
            characterFavoriteRepository.deleteCharacterFavorite(character)
        }
    }
}
