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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import com.vmadalin.android.SampleApp
import com.vmadalin.core.ui.base.BaseFragment
import com.vmadalin.core.ui.utils.RecyclerViewItemDecoration
import com.vmadalin.dynamicfeatures.charactersfavorites.databinding.FragmentCharactersFavoriteListBinding
import com.vmadalin.dynamicfeatures.charactersfavorites.R
import com.vmadalin.dynamicfeatures.charactersfavorites.ui.favorite.adapter.CharactersFavoriteAdapter
import com.vmadalin.dynamicfeatures.charactersfavorites.ui.favorite.adapter.CharactersFavoriteTouchHelper
import com.vmadalin.dynamicfeatures.charactersfavorites.ui.favorite.di.CharactersFavoriteModule
import com.vmadalin.dynamicfeatures.charactersfavorites.ui.favorite.di.DaggerCharactersFavoriteComponent
import javax.inject.Inject

class CharactersFavoriteFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: CharactersFavoriteViewModel
    @Inject
    lateinit var viewAdapter: CharactersFavoriteAdapter

    private lateinit var viewBinding: FragmentCharactersFavoriteListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentCharactersFavoriteListBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, Observer { viewState ->
            when (viewState) {
                is CharactersFavoriteViewState.Listed -> {
                    viewAdapter.submitList(viewState.data)
                }
            }
        })
        viewModel.getAllFavoriteCharacters()
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
        viewBinding.lifecycleOwner = viewLifecycleOwner
        viewBinding.includeList.charactersFavoriteList.apply {
            adapter = viewAdapter
            addItemDecoration(
                RecyclerViewItemDecoration(resources, R.dimen.characters_favorite_list_item_padding)
            )

            ItemTouchHelper(CharactersFavoriteTouchHelper {
                viewModel.removeFavoriteCharacter(viewAdapter.currentList[it])
            }).attachToRecyclerView(this)
        }
    }
}
