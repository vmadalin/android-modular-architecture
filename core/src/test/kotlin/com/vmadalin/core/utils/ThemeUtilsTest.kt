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

package com.vmadalin.core.utils

import android.os.Handler
import androidx.appcompat.app.AppCompatDelegate
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.vmadalin.libraries.testutils.TestCompatActivity
import com.vmadalin.libraries.testutils.robolectric.TestRobolectric
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ThemeUtilsTest : TestRobolectric() {

    @get:Rule
    val rule = ActivityScenarioRule(TestCompatActivity::class.java)
    private lateinit var scenario: ActivityScenario<TestCompatActivity>

    private var themeUtils = ThemeUtilsImpl()

    @Before
    fun setUp() {
        scenario = rule.scenario
    }

    @Test
    fun configuredAppNightMode_ShouldDarkTheme() {
        scenario.onActivity {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            it.delegate.applyDayNight()

            assertTrue(themeUtils.isDarkTheme(it))
        }
    }

    @Test
    fun configuredAppNightMode_ShouldNotLightTheme() {
        scenario.onActivity {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            it.delegate.applyDayNight()

            assertFalse(themeUtils.isLightTheme(it))
        }
    }

    @Test
    fun configuredAppNoNightMode_ShouldBeLightTheme() {
        scenario.onActivity {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            it.delegate.applyDayNight()

            assertTrue(themeUtils.isLightTheme(it))
        }
    }

    @Test
    fun configuredAppNoNightMode_ShouldBeNotDarkTheme() {
        scenario.onActivity {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            it.delegate.applyDayNight()

            assertFalse(themeUtils.isDarkTheme(it))
        }
    }

    @Test
    fun forceAppNightMode_ShouldBeDarkTheme() {
        scenario.onActivity {
            themeUtils.setNightMode(true)
            it.delegate.applyDayNight()

            Handler().postDelayed({ assertTrue(themeUtils.isDarkTheme(it)) }, 300)
        }
    }

    @Test
    fun forceAppNoNightMode_ShouldBeLightTheme() {
        scenario.onActivity {
            themeUtils.setNightMode(false)
            it.delegate.applyDayNight()

            Handler().postDelayed({ assertTrue(themeUtils.isLightTheme(it)) }, 300)
        }
    }
}
