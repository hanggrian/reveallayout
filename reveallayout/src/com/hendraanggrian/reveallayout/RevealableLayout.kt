package com.hendraanggrian.reveallayout

import android.animation.Animator
import android.view.View

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
interface RevealableLayout {

    fun revealTo(source: View, target: View, reverse: Boolean = false): Collection<Animator>

    fun reveal(view: View, startX: Int, startY: Int, reverse: Boolean = false): Animator

    fun reveal(view: View, revealCenter: RevealCenter, reverse: Boolean = false): Animator
            = reveal(view, revealCenter.getX(view), revealCenter.getY(view), reverse)

    fun reveal(view: View, reverse: Boolean = false): Animator
            = reveal(view, RevealCenter.CENTER, reverse)
}