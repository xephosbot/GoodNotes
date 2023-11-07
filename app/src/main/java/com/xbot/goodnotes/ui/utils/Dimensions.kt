package com.xbot.goodnotes.ui.utils

import android.content.res.Resources.getSystem

val Int.dp: Int
    get() = (this / getSystem().displayMetrics.density).toInt()

val Int.px: Int
    get() = (this * getSystem().displayMetrics.density).toInt()

val Float.dp: Float
    get() = this / getSystem().displayMetrics.density

val Float.px: Float
    get() = this * getSystem().displayMetrics.density