package com.vmadalin.dynamicfeatures.characterslist.ui.detail

import com.vmadalin.dynamicfeatures.characterslist.ui.detail.model.CharacterDetail

sealed class CharacterDetailViewState {

    object Loading : CharacterDetailViewState()
    data class Success(val data: CharacterDetail) : CharacterDetailViewState()
    data class Error(val throwable: Throwable) : CharacterDetailViewState()

    fun isLoading() = this is Loading
    fun isSuccess() = this is Success
    fun isError() = this is Error

    fun data(): CharacterDetail? = if (this is Success) this.data else null
}
