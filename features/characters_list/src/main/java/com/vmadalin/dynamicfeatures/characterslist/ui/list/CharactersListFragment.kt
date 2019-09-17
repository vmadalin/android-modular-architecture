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
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.vmadalin.core.base.BaseFragment
import com.vmadalin.dynamicfeatures.characterslist.databinding.FragmentCharactersListBinding

class CharactersListFragment : BaseFragment() {

    private val charactersListViewModel by viewModels<CharactersListViewModel> {
        viewModelFactory
    }

    private var charactersListAdapter: CharactersListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        charactersListAdapter =
            CharactersListAdapter(CharacterClickListener {
            })
        charactersListViewModel.charactersList.observe(
            viewLifecycleOwner,
            Observer { charactersList ->
                charactersListAdapter?.submitList(charactersList)
        })

        val binding = FragmentCharactersListBinding.inflate(inflater, container, false)
        binding.charactersList.adapter = charactersListAdapter
        return binding.root
    }
}
