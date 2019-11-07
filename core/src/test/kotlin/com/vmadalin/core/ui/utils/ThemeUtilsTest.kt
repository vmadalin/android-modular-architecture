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

package com.vmadalin.core.ui.utils

import androidx.appcompat.app.AppCompatDelegate
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.vmadalin.core.TestActivity
import com.vmadalin.core.base.BaseRobolectricTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ThemeUtilsTest : BaseRobolectricTest() {

    @get:Rule
    val rule = ActivityScenarioRule(TestActivity::class.java)
    private lateinit var scenario: ActivityScenario<TestActivity>

    @Before
    fun setUp() {
        scenario = rule.scenario
    }

    @Test
    fun configuredAppNightMode_ShouldDarkTheme() {
        scenario.onActivity {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            it.delegate.applyDayNight()

            assertTrue(ThemeUtils.isDarkTheme(it))
        }
    }

    @Test
    fun configuredAppNightMode_ShouldNotLightTheme() {
        scenario.onActivity {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            it.delegate.applyDayNight()

            assertFalse(ThemeUtils.isLightTheme(it))
        }
    }

    @Test
    fun configuredAppNoNightMode_ShouldBeLightTheme() {
        scenario.onActivity {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            it.delegate.applyDayNight()

            assertTrue(ThemeUtils.isLightTheme(it))
        }
    }

    @Test
    fun configuredAppNoNightMode_ShouldBeNotDarkTheme() {
        scenario.onActivity {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            it.delegate.applyDayNight()

            assertFalse(ThemeUtils.isDarkTheme(it))
        }
    }

    @Test
    fun forceAppNightMode_ShouldBeDarkTheme() {
        scenario.onActivity {
            ThemeUtils.setNightMode(true)
            it.delegate.applyDayNight()

            assertTrue(ThemeUtils.isDarkTheme(it))
        }
    }

    @Test
    fun forceAppNoNightMode_ShouldBeLightTheme() {
        scenario.onActivity {
            ThemeUtils.setNightMode(false)
            it.delegate.applyDayNight()

            assertTrue(ThemeUtils.isLightTheme(it))
        }
    }
}
