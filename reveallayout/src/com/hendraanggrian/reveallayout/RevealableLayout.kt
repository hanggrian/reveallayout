package com.hendraanggrian.reveallayout

import android.animation.Animator
import android.view.Gravity
import android.view.View

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
interface RevealableLayout {

    fun reveal(view: View, reverse: Boolean = false): Animator
            = reveal(view, Gravity.CENTER, reverse)

    fun reveal(view: View, gravity: Int, reverse: Boolean = false): Animator
            = reveal(view, RevealPoint.calculateRevealPoint(view, gravity), reverse)

    fun reveal(view: View, point: RevealPoint, reverse: Boolean = false): Animator

    fun revealTo(source: View, target: View, reverse: Boolean = false): Collection<Animator>
}