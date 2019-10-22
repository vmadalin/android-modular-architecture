package com.vmadalin.dynamicfeatures.charactersfavorites.ui.favorite.adapter.holders

import android.view.LayoutInflater
import com.vmadalin.core.database.characterfavorite.CharacterFavorite
import com.vmadalin.core.ui.base.BaseViewHolder
import com.vmadalin.dynamicfeatures.charactersfavorites.databinding.ListItemCharactersFavoriteBinding

class CharacterFavoriteViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemCharactersFavoriteBinding>(
    binding = ListItemCharactersFavoriteBinding.inflate(inflater)
) {
    fun bind(item: CharacterFavorite) {
        binding.character = item
        binding.executePendingBindings()
    }
}
