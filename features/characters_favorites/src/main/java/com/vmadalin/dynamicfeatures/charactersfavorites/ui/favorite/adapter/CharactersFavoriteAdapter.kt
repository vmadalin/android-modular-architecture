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

package com.vmadalin.dynamicfeatures.charactersfavorites.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vmadalin.core.database.characterfavorite.CharacterFavorite
import com.vmadalin.dynamicfeatures.characterdetail.databinding.ListItemCharactersFavoriteBinding

class CharactersFavoriteAdapter :
    ListAdapter<CharacterFavorite, CharactersFavoriteAdapter.ViewHolder>(
        CharacterDiffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemCharactersFavoriteBinding
                .inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemId(position: Int): Long = position.toLong()

    class ViewHolder(private val binding: ListItemCharactersFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CharacterFavorite) {
            binding.character = item
            binding.executePendingBindings()
        }
    }

    companion object CharacterDiffCallback : DiffUtil.ItemCallback<CharacterFavorite>() {
        override fun areItemsTheSame(
            oldItem: CharacterFavorite,
            newItem: CharacterFavorite
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: CharacterFavorite,
            newItem: CharacterFavorite
        ) = oldItem == newItem
    }
}
