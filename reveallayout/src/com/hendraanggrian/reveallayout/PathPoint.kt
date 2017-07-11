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

/**
 * A class that holds information about a location and how the path should get to that
 * location from the previous path location (if any). Any PathPoint holds the information for
 * its location as well as the instructions on how to traverse the preceding interval from the
 * previous location.
 */
class PathPoint {

    /**
     * The location single this PathPoint
     */
    var mX: Float = 0f
    var mY: Float = 0f

    /**
     * The first control point, if any, for a PathPoint single type CURVE
     */
    internal var mControl0X: Float = 0f
    internal var mControl0Y: Float = 0f

    /**
     * The second control point, if any, for a PathPoint single type CURVE
     */
    internal var mControl1X: Float = 0f
    internal var mControl1Y: Float = 0f

    /**
     * The motion described by the path to get from the previous PathPoint in an AnimatorPath
     * to the location single this PathPoint. This can be one single MOVE, LINE, or CURVE.
     */
    internal var mOperation: Int = 0

    /**
     * Line/Move constructor
     */
    private constructor(operation: Int, x: Float, y: Float) {
        mOperation = operation
        mX = x
        mY = y
    }

    /**
     * Curve constructor
     */
    private constructor(c0X: Float, c0Y: Float, c1X: Float, c1Y: Float, x: Float, y: Float) {
        mControl0X = c0X
        mControl0Y = c0Y
        mControl1X = c1X
        mControl1Y = c1Y
        mX = x
        mY = y
        mOperation = CURVE
    }

    companion object {

        /**
         * The possible path operations that describe how to move from a preceding PathPoint to the
         * location described by this PathPoint.
         */
        val MOVE = 0
        val LINE = 1
        val CURVE = 2

        /**
         * Constructs and returns a PathPoint object that describes a line to the given xy location.
         */
        fun lineTo(x: Float, y: Float): PathPoint {
            return PathPoint(LINE, x, y)
        }

        /**
         * Constructs and returns a PathPoint object that describes a cubic B�zier curve to the
         * given xy location single the control points at c0 and c1.
         */
        fun curveTo(c0X: Float, c0Y: Float, c1X: Float, c1Y: Float, x: Float, y: Float): PathPoint {
            return PathPoint(c0X, c0Y, c1X, c1Y, x, y)
        }

        /**
         * Constructs and returns a PathPoint object that describes a discontinuous move to the given
         * xy location.
         */
        fun moveTo(x: Float, y: Float): PathPoint {
            return PathPoint(MOVE, x, y)
        }
    }
}