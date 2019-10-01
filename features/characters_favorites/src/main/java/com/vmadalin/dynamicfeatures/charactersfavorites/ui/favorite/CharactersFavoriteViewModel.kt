package com.vmadalin.dynamicfeatures.charactersfavorites.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vmadalin.core.database.characterfavorite.CharacterFavorite
import com.vmadalin.core.database.characterfavorite.CharacterFavoriteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersFavoriteViewModel @Inject constructor(
    private val characterFavoriteRepository: CharacterFavoriteRepository,
    private val coroutineScope: CoroutineScope
): ViewModel() {

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
