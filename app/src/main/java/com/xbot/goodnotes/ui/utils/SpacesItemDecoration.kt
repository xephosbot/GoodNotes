package com.xbot.goodnotes.ui.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration(
    private val space: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        val layoutManager = parent.layoutManager as GridLayoutManager
        val spanCount = layoutManager.spanCount

        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        outRect.left = if (column == spanCount - 1) space / 2 else space
        outRect.right = if (column == spanCount - 1) space else space / 2
        outRect.bottom = space
    }
}