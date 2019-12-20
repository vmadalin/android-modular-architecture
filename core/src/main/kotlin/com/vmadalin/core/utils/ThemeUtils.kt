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

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

/**
 * Utils interface for application theme configuration.
 */
interface ThemeUtils {

    /**
     * Whether the current configuration is a dark theme i.e. in Night configuration.
     */
    fun isDarkTheme(context: Context): Boolean

    /**
     * Whether the current configuration is a light theme i.e. in Day configuration.
     */
    fun isLightTheme(context: Context): Boolean

    /**
     * Force [AppCompatDelegate] Mode to night/notnight.
     *
     * @param forceNight Boolean that force night mode otherwise notnight is configured.
     * @param delay Delay to apply mode changes.
     */
    fun setNightMode(forceNight: Boolean, delay: Long = 0L)
}
