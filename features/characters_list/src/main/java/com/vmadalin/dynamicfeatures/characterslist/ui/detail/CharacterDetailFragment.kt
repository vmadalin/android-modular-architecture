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
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.vmadalin.android.SampleApp.Companion.coreComponent
import com.vmadalin.core.ui.base.BaseFragment
import com.vmadalin.core.ui.customviews.ProgressBarDialog
import com.vmadalin.dynamicfeatures.characterslist.R
import com.vmadalin.dynamicfeatures.characterslist.databinding.FragmentCharacterDetailBinding
import com.vmadalin.dynamicfeatures.characterslist.ui.detail.di.CharacterDetailModule
import com.vmadalin.dynamicfeatures.characterslist.ui.detail.di.DaggerCharacterDetailComponent
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_character_detail.*

class CharacterDetailFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: CharacterDetailViewModel

    private lateinit var viewBinding: FragmentCharacterDetailBinding
    private lateinit var viewDialog: ProgressBarDialog

    private val args: CharacterDetailFragmentArgs by navArgs()

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
        viewDialog = ProgressBarDialog(requireContext())
        viewModel.state.observe(viewLifecycleOwner, Observer { viewState ->
            when (viewState) {
                is CharacterDetailViewState.Loading -> {
                    viewDialog.show(R.string.character_detail_dialog_loading_text)
                }
                is CharacterDetailViewState.Success -> {
                    viewDialog.dismiss()
                }
                is CharacterDetailViewState.Error -> {
                    viewDialog.dismissWithErrorMessage(R.string.character_detail_dialog_error_text)
                }
            }
        })
        viewModel.loadCharacterDetail(args.characterId)

        toolbar.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }
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
        viewBinding.viewModel = viewModel
        viewBinding.lifecycleOwner = viewLifecycleOwner
        viewBinding.addFavoriteButton.setOnClickListener {
            viewModel.addCharacterDetailToFavorite()
            Snackbar.make(
                requireView(),
                R.string.character_detail_added_to_favorite_message,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}
