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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vmadalin.core.database.characterfavorite.CharacterFavoriteRepository
import com.vmadalin.core.network.repositiories.MarvelRepository
import com.vmadalin.core.network.responses.BaseResponse
import com.vmadalin.core.network.responses.CharacterResponse
import com.vmadalin.dynamicfeatures.characterslist.ui.detail.model.CharacterDetail
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class CharacterDetailViewModel @Inject constructor(
    private val marvelRepository: MarvelRepository,
    private val characterFavoriteRepository: CharacterFavoriteRepository,
    private val coroutineScope: CoroutineScope
) : ViewModel() {

    private val _state = MutableLiveData<CharacterDetailViewState>()
    val state: LiveData<CharacterDetailViewState>
        get() = _state

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }

    fun loadCharacterDetail(characterId: Long) {
        _state.postValue(CharacterDetailViewState.Loading)
        coroutineScope.launch {
            try {
                val result = marvelRepository.getCharacter(characterId)
                val detail = transform(result)
                _state.postValue(CharacterDetailViewState.Success(detail))
            } catch (e: Exception) {
                _state.postValue(CharacterDetailViewState.Error(e))
            }
        }
    }

    fun addCharacterDetailToFavorite() {
        state.value?.data()?.let {
            coroutineScope.launch {
                characterFavoriteRepository.insertCharacterFavorite(
                    id = it.id,
                    name = it.name,
                    imageUrl = it.imageUrl
                )

                characterFavoriteRepository.getAllCharactersFavorite()
            }
        }
    }

    private fun transform(response: BaseResponse<CharacterResponse>): CharacterDetail {
        val characterResponse = response.data.results.first()
        return CharacterDetail(
            id = characterResponse.id,
            name = characterResponse.name,
            description = characterResponse.description,
            imageUrl = (characterResponse.thumbnail.path +
                "." + characterResponse.thumbnail.extension).replace(
                "http",
                "https"
            )
        )
    }
}
