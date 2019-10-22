package com.vmadalin.dynamicfeatures.characterslist.ui.list.adapter

sealed class CharactersListAdapterState(
    val hasExtraRow: Boolean
) {
    object Loaded : CharactersListAdapterState(hasExtraRow = false)
    object Loading : CharactersListAdapterState(hasExtraRow = true)
    object Error : CharactersListAdapterState(hasExtraRow = true)

    fun isLoaded() = this is Loaded
    fun isLoading() = this is Loading
    fun isError() = this is Error
}
