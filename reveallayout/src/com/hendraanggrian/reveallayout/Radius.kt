package com.hendraanggrian.reveallayout

import android.view.View

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
object Radius {
    const val NONE_DEFAULT = 1
    const val NONE_LARGE = 2
    const val DEFAULT_NONE = 3
    const val LARGE_NONE = 4

    fun getDefaultEndRadius(view: View): Float {
        val cx = (view.left + view.right) / 2
        val cy = (view.top + view.bottom) / 2
        val dx = Math.max(cx, view.width - cx)
        val dy = Math.max(cy, view.height - cy)
        return Math.hypot(dx.toDouble(), dy.toDouble()).toFloat()
    }
}