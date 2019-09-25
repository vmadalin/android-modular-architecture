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

package com.vmadalin.android

import android.app.ActivityOptions
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.TooltipCompat
import androidx.core.graphics.ColorUtils
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.vmadalin.android.utils.ThemeUtils
import com.vmadalin.core.extensions.setGone
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay

class SampleMainActivity : AppCompatActivity() {

    private val navController: NavController by lazy { findNavController(R.id.nav_host_fragment) }
    private val navigationFragmentsId = setOf(
        R.id.characters_list_fragment,
        R.id.characters_favorites_fragment
    )
    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration.Builder(navigationFragmentsId).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()
        setupBottomNavigation()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        menu.findItem(R.id.menu_theme).actionView.apply {
            if (this is AppCompatCheckBox) {
                setButtonDrawable(R.drawable.asl_theme)
                isChecked = ThemeUtils.isDarkTheme(this@SampleMainActivity)
                setOnCheckedChangeListener { _, isChecked ->
                    postDelayed( {
                        AppCompatDelegate.setDefaultNightMode(if (isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
                        delegate.applyDayNight()
                    }, 1000L)
                }
            }
        }
        return true
    }

    // ============================================================================================
    //  Private methods
    // ============================================================================================

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setupBottomNavigation() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottom_navigation.setGone(!navigationFragmentsId.contains(destination.id))
        }
        bottom_navigation.setupWithNavController(navController)
    }
}
