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

package com.vmadalin.dynamicfeatures.dashboard.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vmadalin.core.database.characterfavorite.CharacterFavorite
import com.vmadalin.core.extensions.setupWithNavController
import com.vmadalin.core.ui.utils.ThemeUtils
import com.vmadalin.dynamicfeatures.dashboard.R

class DashboardFragmentt : Fragment() {

    private var currentNavController: LiveData<NavController>? = null
    private val navFragmentsIds = setOf(
        R.id.characters_list_fragment,
        R.id.characters_favorites_fragment
    )
    private val navGraphIds = listOf(
        R.navigation.navigation_characters_list_graph,
        R.navigation.navigation_characters_favorites_graph
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed({
            requireActivity().runOnUiThread {
                setupToolbar()
                if (savedInstanceState == null) {
                    setupBottomNavigationBar(view)
                }
            }
        }, 1000)
    }

//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        setupBottomNavigationBar()
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        return currentNavController?.value?.navigateUp() ?: false
//    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.toolbar_menu, menu)
//
//        menu.findItem(R.id.menu_theme).actionView.apply {
//            if (this is AppCompatCheckBox) {
//                setButtonDrawable(R.drawable.asl_theme)
//                isChecked = ThemeUtils.isDarkTheme(requireContext())
//                setOnCheckedChangeListener { _, isChecked ->
//                    postDelayed({
//                        AppCompatDelegate.setDefaultNightMode(
//                            if (isChecked)
//                                AppCompatDelegate.MODE_NIGHT_YES
//                            else
//                                AppCompatDelegate.MODE_NIGHT_NO
//                        )
//                        delegate.applyDayNight()
//                    }, 1000L)
//                }
//            }
//        }
//        return true
//    }

    // ============================================================================================
    //  Private methods
    // ============================================================================================

    private fun setupToolbar() {
        //setSupportActionBar(toolbar)
    }

    private fun setupBottomNavigationBar(view: View) {
        val test = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = test.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = fragmentManager!!,
            containerId = R.id.nav_host_container,
            intent = requireActivity().intent
        )

        navController.observe(this, Observer {
            it.addOnDestinationChangedListener { _, destination, _ ->
//                if (navFragmentsIds.contains(destination.id)) {
//                    bottom_navigation.setGone(false)
//                    app_bar_layout.setGone(false)
//                } else {
//                    bottom_navigation.setGone(true)
//                    app_bar_layout.setGone(true)
//                }
            }
            //setupActionBarWithNavController(it)
        })
        currentNavController = navController
    }
}
