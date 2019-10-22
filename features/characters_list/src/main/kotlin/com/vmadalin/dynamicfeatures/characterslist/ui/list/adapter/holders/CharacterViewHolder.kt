package com.vmadalin.dynamicfeatures.characterslist.ui.list.adapter.holders

import androidx.recyclerview.widget.RecyclerView
import com.vmadalin.dynamicfeatures.characterslist.databinding.ListItemCharacterBinding
import com.vmadalin.dynamicfeatures.characterslist.ui.list.adapter.CharacterClickListener
import com.vmadalin.dynamicfeatures.characterslist.ui.list.model.CharacterItem

class CharacterViewHolder(private val binding: ListItemCharacterBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(clickListener: CharacterClickListener, item: CharacterItem) {
        binding.clickListener = clickListener
        binding.character = item
        binding.executePendingBindings()
    }
}
