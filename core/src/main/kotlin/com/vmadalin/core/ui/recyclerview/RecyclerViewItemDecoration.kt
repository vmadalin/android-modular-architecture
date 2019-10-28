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

package com.vmadalin.core.ui.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewItemDecoration(
    private val spacingPx: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        setSpacingForDirection(
            outRect = outRect,
            layoutManager = parent.layoutManager,
            position = parent.getChildViewHolder(view).adapterPosition,
            itemCount = state.itemCount
        )
    }

    // ============================================================================================
    //  Private methods
    // ============================================================================================

    private fun setSpacingForDirection(
        outRect: Rect,
        layoutManager: RecyclerView.LayoutManager?,
        position: Int,
        itemCount: Int
    ) {
        outRect.left = spacingPx
        outRect.top = spacingPx

        when (layoutManager) {
            is GridLayoutManager -> {
                val cols = layoutManager.spanCount
                val rows = if (itemCount % 2 == 0) itemCount / cols else (itemCount / cols) + 1

                outRect.right = if (position % cols == cols - 1) spacingPx else 0
                outRect.bottom = if (position / cols == rows - 1) spacingPx else 0
            }
            is RecyclerView.LayoutManager -> {
                if (layoutManager.canScrollHorizontally()) {
                    outRect.right = if (position == itemCount - 1) spacingPx else 0
                    outRect.bottom = spacingPx
                } else if (layoutManager.canScrollVertically()) {
                    outRect.right = spacingPx
                    outRect.bottom = if (position == itemCount - 1) spacingPx else 0
                }
            }
        }
    }
}
