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

package com.vmadalin.dynamicfeatures.charactersfavorites.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import com.vmadalin.android.SampleApp
import com.vmadalin.commons.ui.base.BaseFragment
import com.vmadalin.commons.ui.extensions.observe
import com.vmadalin.core.database.characterfavorite.CharacterFavorite
import com.vmadalin.dynamicfeatures.charactersfavorites.R
import com.vmadalin.dynamicfeatures.charactersfavorites.databinding.FragmentCharactersFavoriteListBinding
import com.vmadalin.dynamicfeatures.charactersfavorites.ui.favorite.adapter.CharactersFavoriteAdapter
import com.vmadalin.dynamicfeatures.charactersfavorites.ui.favorite.adapter.CharactersFavoriteTouchHelper
import com.vmadalin.dynamicfeatures.charactersfavorites.ui.favorite.di.CharactersFavoriteModule
import com.vmadalin.dynamicfeatures.charactersfavorites.ui.favorite.di.DaggerCharactersFavoriteComponent
import javax.inject.Inject

class CharactersFavoriteFragment :
    BaseFragment<FragmentCharactersFavoriteListBinding, CharactersFavoriteViewModel>(
        layoutId = R.layout.fragment_characters_favorite_list
    ) {

    @Inject
    lateinit var viewAdapter: CharactersFavoriteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.data, ::onViewDataChange)
    }

    override fun onInitDependencyInjection() {
        DaggerCharactersFavoriteComponent
            .builder()
            .coreComponent(SampleApp.coreComponent(requireContext()))
            .charactersFavoriteModule(CharactersFavoriteModule(this))
            .build()
            .inject(this)
    }

    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
        viewBinding.includeList.charactersFavoriteList.apply {
            adapter = viewAdapter

            ItemTouchHelper(CharactersFavoriteTouchHelper {
                viewModel.removeFavoriteCharacter(viewAdapter.currentList[it])
            }).attachToRecyclerView(this)
        }
    }

    private fun onViewDataChange(viewData: List<CharacterFavorite>) {
        viewAdapter.submitList(viewData)
    }
}
