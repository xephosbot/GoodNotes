package com.xbot.goodnotes.ui.utils

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max

class GridAutofitLayoutManager(
    context: Context?,
    columnWidth: Int
) : GridLayoutManager(context, 1) {

    private var isColumnWidthChanged = true
    private var lastWidth = 0
    private var lastHeight = 0

    var columnWidth: Int = if (columnWidth <= 0) 48.px else columnWidth
        set(value) {
            field = if (value <= 0) 48.px else value
            if (field != value) {
                isColumnWidthChanged = true
            }
        }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        if (columnWidth > 0 && width > 0 && height > 0 && (isColumnWidthChanged || lastWidth != width || lastHeight != height)) {
            val totalSpace = if (orientation == VERTICAL) {
                width - paddingRight - paddingLeft
            } else {
                height - paddingTop - paddingBottom
            }
            val spanCount = max(1, totalSpace / columnWidth)
            setSpanCount(spanCount)
            isColumnWidthChanged = false
        }
        lastWidth = width
        lastHeight = height

        super.onLayoutChildren(recycler, state)
    }
}
