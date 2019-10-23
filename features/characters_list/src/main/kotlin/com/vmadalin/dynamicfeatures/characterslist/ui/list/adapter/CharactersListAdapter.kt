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
import com.vmadalin.dynamicfeatures.characterslist.ui.list.adapter.holders.CharacterViewHolder
import com.vmadalin.dynamicfeatures.characterslist.ui.list.adapter.holders.ErrorViewHolder
import com.vmadalin.dynamicfeatures.characterslist.ui.list.adapter.holders.LoadingViewHolder
import com.vmadalin.dynamicfeatures.characterslist.ui.list.model.CharacterItem

private enum class ItemView(val type: Int, val span: Int) {
    CHARACTER(type = 0, span = 1),
    LOADING(type = 1, span = 2),
    ERROR(type = 2, span = 2);

    companion object {
        fun valueOf(type: Int): ItemView? = values().first { it.type == type }
    }
}

class CharactersListAdapter(
    private val clickListener: CharacterClickListener
) : BasePagedListAdapter<CharacterItem>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {

    private var state: CharactersListAdapterState = CharactersListAdapterState.Loaded

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (ItemView.valueOf(viewType)) {
            ItemView.CHARACTER -> CharacterViewHolder(inflater)
            ItemView.LOADING -> LoadingViewHolder(inflater)
            else -> ErrorViewHolder(inflater)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemView(position)) {
            ItemView.CHARACTER -> getItem(position)?.let {
                if (holder is CharacterViewHolder) {
                    holder.bind(clickListener, it)
                }
            }
            else -> {}
        }
    }

    override fun getItemCount(): Int {
        return if (state.hasExtraRow) {
            super.getItemCount() + 1
        } else {
            super.getItemCount()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItemView(position).type
    }

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

    fun getSpanSizeLookup(): GridLayoutManager.SpanSizeLookup {
        return object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return getItemView(position).span
            }
        }
    }

    private fun getItemView(position: Int): ItemView {
        return if (state.hasExtraRow && position == itemCount - 1) {
            if (state.isError()) {
                ItemView.ERROR
            } else {
                ItemView.LOADING
            }
        } else {
            ItemView.CHARACTER
        }
    }
}

class CharacterClickListener(val clickListener: (characterId: Long) -> Unit) {
    fun onClick(characterItem: CharacterItem) = clickListener(characterItem.id)
}
