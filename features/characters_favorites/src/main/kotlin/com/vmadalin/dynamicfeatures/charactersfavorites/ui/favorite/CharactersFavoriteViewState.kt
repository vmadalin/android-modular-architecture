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

package com.vmadalin.dynamicfeatures.charactersfavorites.ui.favorite

import com.vmadalin.commons.ui.base.BaseViewState

/**
 * Different states for [CharactersFavoriteFragment].
 *
 * @see BaseViewState
 */
sealed class CharactersFavoriteViewState : BaseViewState {

    /**
     * No favorite characters to display.
     */
    object Empty : CharactersFavoriteViewState()

    /**
     * Favorite characters displayed.
     */
    object Listed : CharactersFavoriteViewState()

    // ============================================================================================
    //  Public helpers methods
    // ============================================================================================

    /**
     * Check if current view state is [Empty].
     *
     * @return True if is empty state, otherwise false.
     */
    fun isEmpty() = this is Empty

    /**
     * Check if current view state is [Listed].
     *
     * @return True if is listed state, otherwise false.
     */
    fun isListed() = this is Listed
}
