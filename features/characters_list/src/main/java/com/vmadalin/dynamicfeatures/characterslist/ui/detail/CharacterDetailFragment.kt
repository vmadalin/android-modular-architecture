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

package com.vmadalin.dynamicfeatures.characterslist.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vmadalin.android.SampleApp.Companion.coreComponent
import com.vmadalin.core.ui.base.BaseFragment
import com.vmadalin.dynamicfeatures.characterslist.databinding.FragmentCharacterDetailBinding
import com.vmadalin.dynamicfeatures.characterslist.ui.detail.di.CharacterDetailModule
import com.vmadalin.dynamicfeatures.characterslist.ui.detail.di.DaggerCharacterDetailComponent
import javax.inject.Inject

class CharacterDetailFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: CharacterDetailViewModel

    private lateinit var viewBinding: FragmentCharacterDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadCharacterDetail()
    }

    override fun onInitDependencyInjection() {
        DaggerCharacterDetailComponent
            .builder()
            .coreComponent(coreComponent(requireContext()))
            .characterDetailModule(CharacterDetailModule(this))
            .build()
            .inject(this)
    }

    override fun onInitDataBinding() {
        //viewBinding.viewModel = viewModel
        viewBinding.lifecycleOwner = viewLifecycleOwner
    }

}
