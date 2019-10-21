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

package com.vmadalin.dynamicfeatures.characterslist.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vmadalin.core.ui.base.BasePagedListAdapter
import com.vmadalin.dynamicfeatures.characterslist.databinding.ListItemCharacterBinding
import com.vmadalin.dynamicfeatures.characterslist.databinding.ListItemErrorBinding
import com.vmadalin.dynamicfeatures.characterslist.databinding.ListItemLoadingBinding
import com.vmadalin.dynamicfeatures.characterslist.ui.list.model.CharacterItem

object ItemViewType {
    const val CHARACTER = 0
    const val LOADING = 1
    const val ERROR = 2
}

class CharactersListAdapter(
    private val clickListener: CharacterClickListener
) : BasePagedListAdapter<CharacterItem>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {

    private var state: CharactersListAdapterState = CharactersListAdapterState.Loaded

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemViewType.CHARACTER -> ViewHolder(
                ListItemCharacterBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    )
                )
            )
            ItemViewType.LOADING -> ViewLoadingHolder(
                ListItemLoadingBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    )
                )
            )
            else -> ViewErrorHolder(ListItemErrorBinding.inflate(LayoutInflater.from(parent.context)))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ItemViewType.CHARACTER -> getItem(position)?.let {
                if (holder is ViewHolder) {
                    holder.bind(clickListener, it)
                }
            }
        }
    }

    fun getSpanSize(position: Int): Int {
        return when (getItemViewType(position)) {
            ItemViewType.LOADING, ItemViewType.ERROR -> 2
            else -> 1
        }
    }

    class ViewHolder(private val binding: ListItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: CharacterClickListener, item: CharacterItem) {
            binding.clickListener = clickListener
            binding.character = item
            binding.executePendingBindings()
        }
    }

    class ViewLoadingHolder(binding: ListItemLoadingBinding) : RecyclerView.ViewHolder(binding.root)
    class ViewErrorHolder(binding: ListItemErrorBinding) : RecyclerView.ViewHolder(binding.root)

    fun submitState(newState: CharactersListAdapterState) {
        if (state.hasExtraRow != newState.hasExtraRow) {
            if (state.hasExtraRow) {
                notifyItemRemoved(itemCount)
            } else {
                notifyItemInserted(itemCount)
            }
        } else if (newState.hasExtraRow && state != newState) {
            notifyItemChanged(itemCount - 1)
        }
        state = newState
    }

    override fun getItemViewType(position: Int): Int {
        return if (state.hasExtraRow && position == itemCount - 1) {
            if (state.isError()) {
                ItemViewType.ERROR
            } else {
                ItemViewType.LOADING
            }
        } else {
            ItemViewType.CHARACTER
        }
    }

    override fun getItemCount(): Int {
        return if (state.hasExtraRow) {
            super.getItemCount() + 1
        } else {
            super.getItemCount()
        }
    }
}

class CharacterClickListener(val clickListener: (characterId: Long) -> Unit) {
    fun onClick(characterItem: CharacterItem) = clickListener(characterItem.id)
}
