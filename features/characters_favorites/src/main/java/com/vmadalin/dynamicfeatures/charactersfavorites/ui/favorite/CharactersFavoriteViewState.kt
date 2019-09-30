package com.vmadalin.dynamicfeatures.charactersfavorites.ui.favorite

import androidx.lifecycle.LiveData
import com.vmadalin.core.database.characterfavorite.CharacterFavorite

sealed class CharactersFavoriteViewState {

    object Empty : CharactersFavoriteViewState()
    data class Listed(val data: List<CharacterFavorite>) : CharactersFavoriteViewState()

    fun isEmpty() = this is Empty
    fun isListed() = this is Listed

    fun data(): List<CharacterFavorite>? = if (this is Listed) this.data else null

}
