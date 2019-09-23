package com.vmadalin.core.ui.utils

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewItemDecoration constructor(
    resources: Resources,
    @DimenRes
    spacingRes: Int
) : RecyclerView.ItemDecoration() {

    private val spacing = resources.getDimension(spacingRes).toInt()

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
        outRect.left = spacing
        outRect.top = spacing

        when (layoutManager) {
            is GridLayoutManager -> {
                val cols = layoutManager.spanCount
                val rows = if (itemCount % 2 == 0) itemCount / cols else (itemCount / cols) + 1

                outRect.right = if (position % cols == cols - 1) spacing else 0
                outRect.bottom = if (position / cols == rows - 1) spacing else 0
            }
            is RecyclerView.LayoutManager -> {
                if (layoutManager.canScrollHorizontally()) {
                    outRect.right = if (position == itemCount - 1) spacing else 0
                    outRect.bottom = spacing
                } else if (layoutManager.canScrollVertically()) {
                    outRect.right = spacing
                    outRect.bottom = if (position == itemCount - 1) spacing else 0
                }
            }
        }
    }
}
