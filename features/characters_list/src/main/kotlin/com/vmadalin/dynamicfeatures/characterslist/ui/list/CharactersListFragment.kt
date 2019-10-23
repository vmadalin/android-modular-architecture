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

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.vmadalin.android.SampleApp.Companion.coreComponent
import com.vmadalin.core.extensions.observe
import com.vmadalin.core.ui.base.BaseFragment
import com.vmadalin.core.ui.utils.RecyclerViewItemDecoration
import com.vmadalin.dynamicfeatures.characterslist.R
import com.vmadalin.dynamicfeatures.characterslist.databinding.FragmentCharactersListBinding
import com.vmadalin.dynamicfeatures.characterslist.ui.list.adapter.CharacterClickListener
import com.vmadalin.dynamicfeatures.characterslist.ui.list.adapter.CharactersListAdapter
import com.vmadalin.dynamicfeatures.characterslist.ui.list.adapter.CharactersListAdapterState
import com.vmadalin.dynamicfeatures.characterslist.ui.list.di.CharactersListModule
import com.vmadalin.dynamicfeatures.characterslist.ui.list.di.DaggerCharactersListComponent
import com.vmadalin.dynamicfeatures.characterslist.ui.list.model.CharacterItem

class CharactersListFragment : BaseFragment<FragmentCharactersListBinding, CharactersListViewModel>(
    layoutId = R.layout.fragment_characters_list
) {

    private lateinit var viewAdapter: CharactersListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.state, ::onViewStateChange)
        observe(viewModel.data, ::onViewDataChange)
    }

    override fun onInitDependencyInjection() {
        DaggerCharactersListComponent
            .builder()
            .coreComponent(coreComponent(requireContext()))
            .charactersListModule(CharactersListModule(this))
            .build()
            .inject(this)
    }

    override fun onInitDataBinding() {
        viewAdapter =
            CharactersListAdapter(CharacterClickListener { characterId ->
                findNavController().navigate(
                    CharactersListFragmentDirections
                        .actionCharactersListFragmentToCharacterDetailFragment(characterId)
                )
            })

        viewBinding.viewModel = viewModel
        viewBinding.includeList.charactersList.apply {
            adapter = viewAdapter
            (layoutManager as GridLayoutManager).spanSizeLookup = viewAdapter.getSpanSizeLookup()
            addItemDecoration(
                RecyclerViewItemDecoration(resources, R.dimen.characters_list_item_padding)
            )
        }

        viewBinding.swipeRefresh.setOnRefreshListener {
            viewModel.refreshLoadedCharactersList()
        }
    }

    private fun onViewDataChange(viewData: PagedList<CharacterItem>) {
        viewAdapter.submitList(viewData)
    }

    private fun onViewStateChange(viewState: CharactersListViewState) {
        if (viewBinding.swipeRefresh.isRefreshing) {
            viewBinding.swipeRefresh.isRefreshing = false
        }
        when (viewState) {
            is CharactersListViewState.Loaded ->
                viewAdapter.submitState(CharactersListAdapterState.Loaded)
            is CharactersListViewState.AddedLoading ->
                viewAdapter.submitState(CharactersListAdapterState.Loading)
            is CharactersListViewState.AddedError ->
                viewAdapter.submitState(CharactersListAdapterState.Error)
        }
    }
}
