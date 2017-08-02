@file:JvmName("Radius")

package com.hendraanggrian.reveallayout

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
object Radius {

    internal const val FROM_GONE = 0x00
    internal const val FROM_DEFAULT = 0x04
    internal const val FROM_ACTIVITY = 0x08
    internal const val FROM_MASK = 0x0C

    internal const val TO_GONE = 0x00
    internal const val TO_DEFAULT = 0x40
    internal const val TO_ACTIVITY = 0x80
    internal const val TO_MASK = 0xC0

    const val GONE_DEFAULT = FROM_GONE or TO_DEFAULT
    const val GONE_ACTIVITY = FROM_GONE or TO_ACTIVITY
    const val DEFAULT_GONE = FROM_DEFAULT or TO_GONE
    const val ACTIVITY_GONE = FROM_ACTIVITY or TO_GONE
}