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

package com.vmadalin.dynamicfeatures.charactersfavorites.ui.favorite.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_IDLE
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

/**
 * Helper class customize touch behavior per [RecyclerView.ViewHolder] allowing to swipe.
 *
 * @see ItemTouchHelper.SimpleCallback
 */
class CharactersFavoriteTouchHelper @Inject constructor(
    private val onSwiped: ((Int) -> Unit)
) : ItemTouchHelper.SimpleCallback(
    ACTION_STATE_IDLE,
    LEFT or RIGHT
) {

    /**
     * Called when ItemTouchHelper wants to move the dragged item from its old position to
     * the new position.
     *
     * @param recyclerView The RecyclerView to which ItemTouchHelper is attached to.
     * @param viewHolder The ViewHolder which is being dragged by the user.
     * @param target The ViewHolder over which the currently active item is being dragged.
     * @return True if the viewHolder has been moved to the adapter position.
     * @see ItemTouchHelper.SimpleCallback.onMove
     */
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ) = true

    /**
     * Called when a ViewHolder is swiped by the user.
     *
     * @param viewHolder The ViewHolder which has been swiped by the user.
     * @param direction The direction to which the ViewHolder is swiped. It is one of
     * [ItemTouchHelper.UP], [ItemTouchHelper.DOWN], [ItemTouchHelper.LEFT] or [ItemTouchHelper.RIGHT].
     * @see ItemTouchHelper.SimpleCallback.onSwiped
     */
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        onSwiped(viewHolder.adapterPosition)
    }
}
