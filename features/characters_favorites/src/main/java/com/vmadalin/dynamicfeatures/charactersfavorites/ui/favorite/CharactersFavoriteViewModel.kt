package com.vmadalin.dynamicfeatures.charactersfavorites.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vmadalin.core.database.characterfavorite.CharacterFavoriteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersFavoriteViewModel @Inject constructor(
    characterFavoriteRepository: CharacterFavoriteRepository,
    coroutineScope: CoroutineScope
): ViewModel() {

    private val _state = MutableLiveData<CharactersFavoriteViewState>()
    val state: LiveData<CharactersFavoriteViewState>
        get() = _state

    init {
        coroutineScope.launch {
            val charactersFavorite = characterFavoriteRepository.getAllCharactersFavorite()
            if (charactersFavorite.isEmpty()) {
                _state.postValue(CharactersFavoriteViewState.Empty)
            } else {
                _state.postValue(CharactersFavoriteViewState.Listed(charactersFavorite))
            }
        }
    }
}
