package com.hendraanggrian.reveallayout

import android.os.Build
import android.support.v4.view.ViewCompat
import android.view.Gravity
import android.view.View
import com.hendraanggrian.support.utils.content.isRtl

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
data class RevealPoint(val x: Int, val y: Int) {

    companion object {
        internal fun calculateRevealPoint(view: View, gravity: Int): RevealPoint {
            val absoluteGravity = Gravity.getAbsoluteGravity(gravity, if (Build.VERSION.SDK_INT >= 17 && view.context.isRtl())
                View.LAYOUT_DIRECTION_RTL
            else
                ViewCompat.LAYOUT_DIRECTION_LTR)
            var x = 0
            when (absoluteGravity and Gravity.HORIZONTAL_GRAVITY_MASK) {
                Gravity.LEFT, Gravity.START -> x = 0
                Gravity.CENTER_HORIZONTAL -> x = view.width / 2
                Gravity.RIGHT, Gravity.END -> x = view.width
            }
            var y = 0
            when (absoluteGravity and Gravity.VERTICAL_GRAVITY_MASK) {
                Gravity.TOP -> y = 0
                Gravity.CENTER_VERTICAL -> y = view.height / 2
                Gravity.BOTTOM -> y = view.height
            }
            return RevealPoint(x, y)
        }
    }
}