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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.vmadalin.android.SampleApp.Companion.coreComponent
import com.vmadalin.core.ui.base.BaseFragment
import com.vmadalin.core.ui.utils.RecyclerViewItemDecoration
import com.vmadalin.dynamicfeatures.characterslist.R
import com.vmadalin.dynamicfeatures.characterslist.databinding.FragmentCharactersListBinding
import com.vmadalin.dynamicfeatures.characterslist.ui.list.di.CharactersListModule
import com.vmadalin.dynamicfeatures.characterslist.ui.list.di.DaggerCharactersListComponent
import javax.inject.Inject

class CharactersListFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: CharactersListViewModel

    private lateinit var viewBinding: FragmentCharactersListBinding
    private lateinit var viewAdapter: CharactersListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentCharactersListBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.charactersList.observe(viewLifecycleOwner) {
            if (viewBinding.swipeRefresh.isRefreshing) {
                viewBinding.swipeRefresh.isRefreshing = false
            }
            viewAdapter.submitList(it)
        }
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
        viewAdapter = CharactersListAdapter(CharacterClickListener { characterId ->
            findNavController().navigate(
                CharactersListFragmentDirections.actionCharactersListFragmentToCharacterDetailFragment(characterId)
            )
        })

        viewBinding.charactersList.addItemDecoration(RecyclerViewItemDecoration(resources, R.dimen.character_list_item_padding))
        viewBinding.charactersList.adapter = viewAdapter
        viewBinding.lifecycleOwner = viewLifecycleOwner
        viewBinding.swipeRefresh.setOnRefreshListener {
            viewModel.refreshCharactersList()
        }
    }
}
