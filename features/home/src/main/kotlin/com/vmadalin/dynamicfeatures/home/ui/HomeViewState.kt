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

import com.vmadalin.commons.ui.base.BaseViewState

/**
 * Different states for [HomeFragment].
 *
 * @see BaseViewState
 */
sealed class HomeViewState : BaseViewState {

    /**
     * Full screen mode.
     */
    object FullScreen : HomeViewState()

    /**
     * Navigation screen mode with bottom bar.
     */
    object NavigationScreen : HomeViewState()

    // ============================================================================================
    //  Public helpers methods
    // ============================================================================================

    /**
     * Check if current view state is [FullScreen].
     *
     * @return True if is full screen state, otherwise false.
     */
    fun isFullScreen() = this is FullScreen

    /**
     * Check if current view state is [NavigationScreen].
     *
     * @return True if is navigation screen state, otherwise false.
     */
    fun isNavigationScreen() = this is NavigationScreen
}
