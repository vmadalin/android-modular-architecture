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

package com.vmadalin.dynamicfeatures.home.ui

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.lifecycle.Observer
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.vmadalin.android.SampleApp
import com.vmadalin.core.extensions.setupWithNavController
import com.vmadalin.core.ui.base.BaseFragment
import com.vmadalin.core.ui.utils.ThemeUtils
import com.vmadalin.dynamicfeatures.home.R
import com.vmadalin.dynamicfeatures.home.databinding.FragmentHomeBinding
import com.vmadalin.dynamicfeatures.home.ui.di.DaggerHomeComponent
import com.vmadalin.dynamicfeatures.home.ui.di.HomeModule
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    layoutId = R.layout.fragment_home
) {

    @Inject
    lateinit var viewModel: HomeViewModel

    private val navGraphIds = listOf(
        R.navigation.navigation_characters_list_graph,
        R.navigation.navigation_characters_favorites_graph
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        Handler().postDelayed({
            if (savedInstanceState == null) {
                setupBottomNavigationBar()
            }
        }, 1000)
    }

    override fun onInitDependencyInjection() {
        DaggerHomeComponent
            .builder()
            .coreComponent(SampleApp.coreComponent(requireContext()))
            .homeModule(HomeModule(this))
            .build()
            .inject(this)
    }

    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
        viewBinding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu, menu)

        menu.findItem(R.id.menu_theme).actionView.apply {
            if (this is AppCompatCheckBox) {
                setButtonDrawable(R.drawable.asl_theme)
                isChecked = ThemeUtils.isDarkTheme(requireContext())
                setOnCheckedChangeListener { _, isChecked ->
                    postDelayed({
                        AppCompatDelegate.setDefaultNightMode(
                            if (isChecked)
                                AppCompatDelegate.MODE_NIGHT_YES
                            else
                                AppCompatDelegate.MODE_NIGHT_NO
                        )
                        requireCompatActivity().delegate.applyDayNight()
                    }, 1000L)
                }
            }
        }
    }

    // ============================================================================================
    //  Private methods
    // ============================================================================================

    private fun setupToolbar() {
        setHasOptionsMenu(true)
        requireCompatActivity().setSupportActionBar(viewBinding.toolbar)
    }

    private fun setupBottomNavigationBar() {
        val navController = viewBinding.bottomNavigation.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = fragmentManager!!,
            containerId = R.id.nav_host_container,
            intent = requireActivity().intent
        )

        navController.observe(viewLifecycleOwner, Observer {
            viewModel.navigationControllerChanged(it)
            setupActionBarWithNavController(requireCompatActivity(), it)
        })
    }
}
