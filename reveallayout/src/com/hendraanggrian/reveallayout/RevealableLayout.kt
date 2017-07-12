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
import com.hendraanggrian.support.utils.content.isRtl
import io.codetail.animation.ViewAnimationUtils

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
interface RevealableLayout {

    fun revealTo(source: View,
                 target: View,
                 startRadius: Float,
                 endRadius: Float): Collection<Animator> {
        // Cancel all concurrent events on view
        if (Build.VERSION.SDK_INT >= 19) {
            source.cancelPendingInputEvents()
        }

        // Coordinates of circle initial point
        val parent = target.parent as ViewGroup
        val srcRect = createRect(parent, source)
        val trgRect = createRect(parent, target)
        val circularAnimator = ViewAnimationUtils.createCircularReveal(target, trgRect.centerX(), trgRect.centerY(), startRadius, endRadius)

        // Put Mask view at circle 8initial points
        //target.setX(srcRect.left - trgRect.centerX());
        //target.setY(srcRect.top - trgRect.centerY());

        val c0X = (srcRect.centerX() - trgRect.centerX()).toFloat()
        val c0Y = (srcRect.centerY() - trgRect.centerY()).toFloat()
        val path = AnimatorPath()
        if (startRadius <= endRadius) {
            path.moveTo(c0X, c0Y)
            path.curveTo(c0X, c0Y, 0f, c0Y, trgRect.left.toFloat(), trgRect.top.toFloat())
        } else {
            path.moveTo(trgRect.left.toFloat(), trgRect.top.toFloat())
            path.curveTo(trgRect.left.toFloat(), trgRect.top.toFloat(), 0f, c0Y, c0X, c0Y)
        }
        val pathAnimator = ObjectAnimator.ofObject(object : OnMaskListener {
            override fun setMaskLocation(point: PathPoint) {
                target.x = point.mX
                target.y = point.mY
            }
        }, "maskLocation", PathEvaluator(), *path.points.toTypedArray())

        return listOf(circularAnimator, pathAnimator)
    }

    fun revealTo(view: View,
                 target: View,
                 radiusFlag: Int = Radius.NONE_DEFAULT): Collection<Animator> {
        val (startRadius, endRadius) = calculateRadius(view, radiusFlag)
        return revealTo(view, target, startRadius, endRadius)
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
        return reveal(view, centerX, centerY, startRadius, endRadius)
    }

    fun reveal(view: View,
               centerX: Int,
               centerY: Int,
               radiusFlag: Int = Radius.NONE_DEFAULT): Animator {
        val (startRadius, endRadius) = calculateRadius(view, radiusFlag)
        return reveal(view, centerX, centerY, startRadius, endRadius)
    }

    fun reveal(view: View,
               centerGravity: Int = Gravity.CENTER,
               radiusFlag: Int = Radius.NONE_DEFAULT): Animator {
        val (centerX, centerY) = calculateCenter(view, centerGravity)
        val (startRadius, endRadius) = calculateRadius(view, radiusFlag)
        return reveal(view, centerX, centerY, startRadius, endRadius)
    }

    companion object {
        private fun createRect(parent: ViewGroup, view: View): Rect {
            val rect = Rect()
            view.getDrawingRect(rect)
            parent.offsetDescendantRectToMyCoords(view, rect)
            return rect
        }

        private fun calculateRadius(view: View, flag: Int): Pair<Float, Float> {
            var start: Float = 0f
            var end: Float = 0f
            val default = Radius.getDefaultEndRadius(view)
            when (flag) {
                Radius.NONE_DEFAULT -> end = default
                Radius.NONE_LARGE -> end = default * 2
                Radius.DEFAULT_NONE -> start = default
                Radius.LARGE_NONE -> start = default * 2
            }
            return Pair(start, end)
        }

        @SuppressLint("RtlHardcoded")
        private fun calculateCenter(view: View, gravity: Int): Pair<Int, Int> {
            val absoluteGravity = Gravity.getAbsoluteGravity(gravity, if (Build.VERSION.SDK_INT >= 17 && view.context.isRtl())
                View.LAYOUT_DIRECTION_RTL
            else
                ViewCompat.LAYOUT_DIRECTION_LTR)
            var x: Int = 0
            when (absoluteGravity and Gravity.HORIZONTAL_GRAVITY_MASK) {
                Gravity.LEFT, Gravity.START -> x = 0
                Gravity.CENTER_HORIZONTAL -> x = view.width / 2
                Gravity.RIGHT, Gravity.END -> x = view.width
            }
            var y: Int = 0
            when (absoluteGravity and Gravity.VERTICAL_GRAVITY_MASK) {
                Gravity.TOP -> y = 0
                Gravity.CENTER_VERTICAL -> y = view.height / 2
                Gravity.BOTTOM -> y = view.height
            }
            return Pair(x, y)
        }
    }
}