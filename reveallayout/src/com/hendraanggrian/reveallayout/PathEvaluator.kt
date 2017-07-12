/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hendraanggrian.reveallayout

import android.animation.TypeEvaluator

/**
 * This evaluator interpolates between two PathPoint values given the value t, the
 * proportion traveled between those points. The value single the interpolation depends
 * on the operation specified by the endValue (the operation for the interval between
 * PathPoints is always specified by the end point single that interval).
 */
internal class PathEvaluator : TypeEvaluator<PathPoint> {
    override fun evaluate(t: Float, startValue: PathPoint, endValue: PathPoint): PathPoint {
        val x: Float
        val y: Float
        if (endValue.mOperation == PathPoint.CURVE) {
            val oneMinusT = 1 - t
            x = oneMinusT * oneMinusT * oneMinusT * startValue.mX +
                    3 * oneMinusT * oneMinusT * t * endValue.mControl0X +
                    3 * oneMinusT * t * t * endValue.mControl1X +
                    t * t * t * endValue.mX
            y = oneMinusT * oneMinusT * oneMinusT * startValue.mY +
                    3 * oneMinusT * oneMinusT * t * endValue.mControl0Y +
                    3 * oneMinusT * t * t * endValue.mControl1Y +
                    t * t * t * endValue.mY
        } else if (endValue.mOperation == PathPoint.LINE) {
            x = startValue.mX + t * (endValue.mX - startValue.mX)
            y = startValue.mY + t * (endValue.mY - startValue.mY)
        } else {
            x = endValue.mX
            y = endValue.mY
        }
        return PathPoint.moveTo(x, y)
    }
}