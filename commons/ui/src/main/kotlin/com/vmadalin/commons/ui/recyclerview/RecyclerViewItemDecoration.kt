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

package com.vmadalin.commons.ui.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.ceil

/**
 * Simple item decoration allows the application to add a special drawing and layout offset
 * to specific item views from the adapter's data set. Support the grid and linear layout.
 *
 * @see RecyclerView.ItemDecoration
 */
class RecyclerViewItemDecoration(
    @VisibleForTesting(otherwise = PRIVATE)
    internal val spacingPx: Int
) : RecyclerView.ItemDecoration() {

    /**
     * Retrieve any offsets for the given item.
     *
     * @param outRect Rect to receive the output.
     * @param view The child view to decorate
     * @param parent RecyclerView this ItemDecoration is decorating
     * @param state The current state of RecyclerView.
     * @see RecyclerView.ItemDecoration.getItemOffsets
     */
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        when (val layoutManager = parent.layoutManager) {
            is GridLayoutManager -> configSpacingForGridLayoutManager(
                outRect = outRect,
                layoutManager = layoutManager,
                position = parent.getChildViewHolder(view).adapterPosition,
                itemCount = state.itemCount
            )
            is LinearLayoutManager -> configSpacingForLinearLayoutManager(
                outRect = outRect,
                layoutManager = layoutManager,
                position = parent.getChildViewHolder(view).adapterPosition,
                itemCount = state.itemCount
            )
        }
    }

    // ============================================================================================
    //  Private configs methods
    // ============================================================================================

    /**
     * Configure spacing for grid layout, given a rectangle.
     *
     * @param outRect Rect to modify.
     * @param layoutManager The currently responsible for layout policy.
     * @param position Position of the item represented by this ViewHolder.
     * @param itemCount The total number of items that can be laid out.
     */
    private fun configSpacingForGridLayoutManager(
        outRect: Rect,
        layoutManager: GridLayoutManager,
        position: Int,
        itemCount: Int
    ) {
        val cols = layoutManager.spanCount
        val rows = ceil(itemCount / cols.toDouble()).toInt()

        outRect.top = spacingPx
        outRect.left = spacingPx
        outRect.right = if (position % cols == cols - 1) spacingPx else 0
        outRect.bottom = if (position / cols == rows - 1) spacingPx else 0
    }

    /**
     * Configure spacing for linear layout, given a rectangle.
     *
     * @param outRect Rect to modify.
     * @param layoutManager The currently responsible for layout policy.
     * @param position Position of the item represented by this ViewHolder.
     * @param itemCount The total number of items that can be laid out.
     */
    private fun configSpacingForLinearLayoutManager(
        outRect: Rect,
        layoutManager: LinearLayoutManager,
        position: Int,
        itemCount: Int
    ) {
        outRect.top = spacingPx
        outRect.left = spacingPx
        if (layoutManager.canScrollHorizontally()) {
            outRect.right = if (position == itemCount - 1) spacingPx else 0
            outRect.bottom = spacingPx
        } else if (layoutManager.canScrollVertically()) {
            outRect.right = spacingPx
            outRect.bottom = if (position == itemCount - 1) spacingPx else 0
        }
    }
}
