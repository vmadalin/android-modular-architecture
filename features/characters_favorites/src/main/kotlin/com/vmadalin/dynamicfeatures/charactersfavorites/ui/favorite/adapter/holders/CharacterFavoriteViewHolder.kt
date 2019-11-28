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

package com.vmadalin.dynamicfeatures.charactersfavorites.ui.favorite.adapter.holders

import android.view.LayoutInflater
import com.vmadalin.commons.ui.base.BaseViewHolder
import com.vmadalin.core.database.characterfavorite.CharacterFavorite
import com.vmadalin.dynamicfeatures.charactersfavorites.databinding.ListItemCharactersFavoriteBinding

class CharacterFavoriteViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemCharactersFavoriteBinding>(
    binding = ListItemCharactersFavoriteBinding.inflate(inflater)
) {
    fun bind(characterFavorite: CharacterFavorite) {
        binding.character = characterFavorite
        binding.executePendingBindings()
    }
}
