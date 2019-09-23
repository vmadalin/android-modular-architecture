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

package com.vmadalin.dynamicfeatures.characterslist.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vmadalin.dynamicfeatures.characterslist.databinding.ListItemCharacterBinding
import com.vmadalin.dynamicfeatures.characterslist.models.CharacterItem

class CharactersListAdapter(private val clickListener: CharacterClickListener) :
    ListAdapter<CharacterItem, CharactersListAdapter.ViewHolder>(CharacterDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemCharacterBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(clickListener, it) }
    }

    class ViewHolder(private val binding: ListItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: CharacterClickListener, item: CharacterItem) {
            binding.clickListener = clickListener
            binding.character = item
            binding.executePendingBindings()
        }
    }

    companion object CharacterDiffCallback : DiffUtil.ItemCallback<CharacterItem>() {
        override fun areItemsTheSame(oldItem: CharacterItem, newItem: CharacterItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterItem, newItem: CharacterItem): Boolean {
            return oldItem == newItem
        }
    }
}

class CharacterClickListener(val clickListener: (characterId: Long) -> Unit) {
    fun onClick(characterItem: CharacterItem) = clickListener(characterItem.id)
}
