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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vmadalin.core.database.characterfavorite.CharacterFavorite
import com.vmadalin.core.database.characterfavorite.CharacterFavoriteRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class CharactersFavoriteViewModel @Inject constructor(
    private val characterFavoriteRepository: CharacterFavoriteRepository,
    private val coroutineScope: CoroutineScope
) : ViewModel() {

    private val _state = MutableLiveData<CharactersFavoriteViewState>()
    val state: LiveData<CharactersFavoriteViewState>
        get() = _state

    init {
        getAllFavoriteCharacters()
    }

    fun getAllFavoriteCharacters() {
        coroutineScope.launch {
            val charactersFavorite = characterFavoriteRepository.getAllCharactersFavorite()
            if (charactersFavorite.isEmpty()) {
                _state.postValue(CharactersFavoriteViewState.Empty)
            } else {
                _state.postValue(CharactersFavoriteViewState.Listed(charactersFavorite))
            }
        }
    }

    fun removeFavoriteCharacter(character: CharacterFavorite) {
        coroutineScope.launch {
            characterFavoriteRepository.deleteCharacterFavorite(character)
            getAllFavoriteCharacters()
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}
