package com.hendraanggrian.reveallayout

import android.animation.Animator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Build
import android.support.v4.view.ViewCompat
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.hendraanggrian.kota.content.isRtl
import io.codetail.animation.ViewAnimationUtils

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
interface RevealableLayout {

    fun revealTo(source: View,
                 target: View,
                 radiusFlag: Int = Radius.GONE_DEFAULT): MutableCollection<Animator> {
        // Coordinates of circle initial point
        val srcRect = createRect(source)
        val trgRect = createRect(target)
        val (startRadius, endRadius) = calculateRevealRadius(target, radiusFlag, srcRect.width().toFloat() / 2, srcRect.width().toFloat() / 2)
        val circularAnimator = ViewAnimationUtils.createCircularReveal(target, trgRect.centerX(), trgRect.centerY(), startRadius, endRadius)

        // Put Mask view at circle 8initial points
        // target.setX(srcRect.left - trgRect.centerX());
        // target.setY(srcRect.top - trgRect.centerY());

        val c0X = srcRect.centerX().toFloat() - trgRect.centerX().toFloat()
        val c0Y = srcRect.centerY().toFloat() - trgRect.centerY().toFloat()
        val path = AnimatorPath()
        if (radiusFlag and Radius.FROM_MASK == Radius.FROM_GONE) {
            path.moveTo(c0X, c0Y)
            path.curveTo(c0X, c0Y, 0f, c0Y, trgRect.left.toFloat(), trgRect.top.toFloat())
        } else {
            path.moveTo(trgRect.left.toFloat(), trgRect.top.toFloat())
            path.curveTo(trgRect.left.toFloat(), trgRect.top.toFloat(), 0f, c0Y, c0X, c0Y)
        }
        val pathAnimator = ObjectAnimator.ofObject(OnMaskListener { point ->
            target.x = point.mX
            target.y = point.mY
        }, "maskLocation", PathEvaluator(), *path.points.toTypedArray())

        return mutableListOf(circularAnimator, pathAnimator)
    }

    fun reveal(view: View,
               centerX: Int,
               centerY: Int,
               startRadius: Float,
               endRadius: Float): Animator {
        return ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius)
    }

    fun reveal(view: View,
               centerGravity: Int = Gravity.CENTER,
               startRadius: Float,
               endRadius: Float): Animator {
        val (centerX, centerY) = calculateCenter(view, centerGravity)
        return ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius)
    }

    fun reveal(view: View,
               centerX: Int,
               centerY: Int,
               radiusFlag: Int = Radius.GONE_DEFAULT): Animator {
        val (startRadius, endRadius) = calculateRevealRadius(view, radiusFlag)
        return ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius)
    }

    fun reveal(view: View,
               centerGravity: Int = Gravity.CENTER,
               radiusFlag: Int = Radius.GONE_DEFAULT): Animator {
        val (centerX, centerY) = calculateCenter(view, centerGravity)
        val (startRadius, endRadius) = calculateRevealRadius(view, radiusFlag)
        return ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius)
    }

    companion object {
        private fun createRect(view: View) = Rect().apply {
            view.getDrawingRect(this)
            (view.parent as ViewGroup).offsetDescendantRectToMyCoords(view, this)
        }

        private fun calculateRevealRadius(view: View, flag: Int, defaultStart: Float = 0f, defaultEnd: Float = 0f): Pair<Float, Float> {
            var start = defaultStart
            var end = defaultEnd
            getRevealEndRadius(view).let {
                when (flag) {
                    Radius.GONE_DEFAULT -> end = it
                    Radius.GONE_ACTIVITY -> end = it * 2
                    Radius.DEFAULT_GONE -> start = it
                    Radius.ACTIVITY_GONE -> start = it * 2
                }
            }
            return Pair(start, end)
        }

        private fun getRevealEndRadius(view: View): Float {
            val cx = (view.left + view.right) / 2
            val cy = (view.top + view.bottom) / 2
            val dx = Math.max(cx, view.width - cx)
            val dy = Math.max(cy, view.height - cy)
            return Math.hypot(dx.toDouble(), dy.toDouble()).toFloat()
        }

        @SuppressLint("RtlHardcoded")
        private fun calculateCenter(view: View, gravity: Int): Pair<Int, Int> {
            var x = 0
            var y = 0
            Gravity.getAbsoluteGravity(gravity, if (Build.VERSION.SDK_INT >= 17 && view.context.isRtl()) View.LAYOUT_DIRECTION_RTL else ViewCompat.LAYOUT_DIRECTION_LTR).let {
                when (it and Gravity.HORIZONTAL_GRAVITY_MASK) {
                    Gravity.LEFT, Gravity.START -> x = 0
                    Gravity.CENTER_HORIZONTAL -> x = view.width / 2
                    Gravity.RIGHT, Gravity.END -> x = view.width
                }
                when (it and Gravity.VERTICAL_GRAVITY_MASK) {
                    Gravity.TOP -> y = 0
                    Gravity.CENTER_VERTICAL -> y = view.height / 2
                    Gravity.BOTTOM -> y = view.height
                }
            }
            return Pair(x, y)
        }
    }
}