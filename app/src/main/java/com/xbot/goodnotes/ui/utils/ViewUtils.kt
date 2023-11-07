package com.xbot.goodnotes.ui.utils

import android.os.Build
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment

/**
 * Updates the system bar insets for this view.
 *
 * @param edges An array of edges for which to update the insets. Valid values: Gravity.START, Gravity.TOP, Gravity.END, Gravity.BOTTOM.
 */
fun View.fitSystemBars(vararg edges: Int) = ViewCompat.setOnApplyWindowInsetsListener(this) { _, windowInsets ->
    val isLtr = ViewCompat.getLayoutDirection(this) == ViewCompat.LAYOUT_DIRECTION_LTR
    val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
    val newPadding = intArrayOf(paddingLeft, paddingTop, paddingRight, paddingBottom)

    edges.forEach { edge ->
        when (edge) {
            Gravity.START -> newPadding[0] += insets.left
            Gravity.TOP -> newPadding[1] += insets.top
            Gravity.END -> newPadding[2] += insets.right
            Gravity.BOTTOM -> newPadding[3] += insets.bottom
        }
    }

    updatePadding(
        left = if (isLtr) newPadding[0] else newPadding[2],
        top = newPadding[1],
        right = if (isLtr) newPadding[2] else newPadding[0],
        bottom = newPadding[3]
    )
    WindowInsetsCompat.CONSUMED
}

/**
 * Sets the background color of a View to a color resource.
 *
 * @param id The resource id of the color to set as the background color.
 */
fun View.setBackgroundColorCompat(@ColorRes id: Int) {
    setBackgroundColor(ContextCompat.getColor(context, id))
}

/**
 * Sets the transition name of a View.
 *
 * @param transitionName The transition name to set.
 */
fun View.setTransitionNameCompat(transitionName: String) {
    ViewCompat.setTransitionName(this, transitionName)
}

/**
 * Initiates the postponed transition after the view has been drawn.
 *
 * @param view The view for which the transition needs to be fixed.
 */
fun Fragment.startPostponedTransitionAfterPreDraw(view: View) {
    postponeEnterTransition()
    (view.parent as ViewGroup).viewTreeObserver.addOnPreDrawListener {
        startPostponedEnterTransition()
        true
    }
}

/**
 * Sets the appearance of system bars to light or dark. If the value is null, it reverts to the default appearance defined in the theme.
 *
 * Note: This method has no effect on API < 23.
 *
 * @param value A Boolean value that determines whether the system bars should be light (true), dark (false), or default (null).
 */
fun Fragment.setIsAppearanceLightSystemBars(value: Boolean? = null) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return

    val context = requireContext()
    val typedValue = TypedValue()

    context.theme.resolveAttribute(android.R.attr.windowLightStatusBar, typedValue, true)
    val isStatusBarLight = typedValue.data != 0

    var isNavigationBarLight = false
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
        context.theme.resolveAttribute(android.R.attr.windowLightNavigationBar, typedValue, true)
        isNavigationBarLight = typedValue.data != 0
    }

    val window = requireActivity().window
    val decorView = window.decorView
    val windowInsetsController = WindowInsetsControllerCompat(window, decorView)
    windowInsetsController.isAppearanceLightStatusBars = value ?: isStatusBarLight
    windowInsetsController.isAppearanceLightNavigationBars = value ?: isNavigationBarLight
}

/**
 * Sets the appearance of system bars to light or dark. If the value is null, it reverts to the default appearance defined in the theme.
 *
 * Note: This method has no effect on API < 23.
 *
 * @param value A Boolean value that determines whether the system bars should be light (true), dark (false), or default (null).
 */
fun AppCompatActivity.setIsAppearanceLightSystemBars(value: Boolean? = null) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return

    val typedValue = TypedValue()

    theme.resolveAttribute(android.R.attr.windowLightStatusBar, typedValue, true)
    val isStatusBarLight = typedValue.data != 0

    var isNavigationBarLight = false
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
        theme.resolveAttribute(android.R.attr.windowLightNavigationBar, typedValue, true)
        isNavigationBarLight = typedValue.data != 0
    }

    val decorView = window.decorView
    val windowInsetsController = WindowInsetsControllerCompat(window, decorView)
    windowInsetsController.isAppearanceLightStatusBars = value ?: isStatusBarLight
    windowInsetsController.isAppearanceLightNavigationBars = value ?: isNavigationBarLight
}
