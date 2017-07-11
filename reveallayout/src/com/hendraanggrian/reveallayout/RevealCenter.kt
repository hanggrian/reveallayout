package com.hendraanggrian.reveallayout

import android.view.View

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
enum class RevealCenter private constructor(val attrId: Int) {
    TOP_START(0) {
        override fun getX(view: View): Int {
            return 0
        }

        override fun getY(view: View): Int {
            return 0
        }
    },
    TOP_CENTER(1) {
        override fun getX(view: View): Int {
            return view.width / 2
        }

        override fun getY(view: View): Int {
            return 0
        }
    },
    TOP_END(2) {
        override fun getX(view: View): Int {
            return view.width
        }

        override fun getY(view: View): Int {
            return 0
        }
    },

    START(3) {
        override fun getX(view: View): Int {
            return 0
        }

        override fun getY(view: View): Int {
            return view.height / 2
        }
    },
    CENTER(4) {
        override fun getX(view: View): Int {
            return view.width / 2
        }

        override fun getY(view: View): Int {
            return view.height / 2
        }
    },
    END(5) {
        override fun getX(view: View): Int {
            return view.width
        }

        override fun getY(view: View): Int {
            return view.height / 2
        }
    },

    BOTTOM_START(6) {
        override fun getX(view: View): Int {
            return 0
        }

        override fun getY(view: View): Int {
            return view.height
        }
    },
    BOTTOM_CENTER(7) {
        override fun getX(view: View): Int {
            return view.width / 2
        }

        override fun getY(view: View): Int {
            return view.height
        }
    },
    BOTTOM_END(8) {
        override fun getX(view: View): Int {
            return view.width
        }

        override fun getY(view: View): Int {
            return view.height
        }
    };

    abstract fun getX(view: View): Int

    abstract fun getY(view: View): Int

    companion object {

        fun valueOf(attrId: Int): RevealCenter {
            for (revealCenter in values())
                if (revealCenter.attrId == attrId)
                    return revealCenter
            throw RuntimeException()
        }
    }
}