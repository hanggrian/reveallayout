package com.hendraanggrian.reveallayout

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
internal abstract class RevealLayoutImpl(context: Context, attrs: AttributeSet?) {

    companion object {
        private const val UNSPECIFIED_ID = -1
        private const val UNSPECIFIED_DURATION = -1
    }

    internal abstract val layout: RevealableLayout
    private val revealId: Int
    private val revealDuration: Int
    private val revealGravity: Int

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.RevealLayout)
        revealId = a.getResourceId(R.styleable.RevealLayout_revealId, UNSPECIFIED_ID)
        revealDuration = a.getInt(R.styleable.RevealLayout_revealDuration, UNSPECIFIED_DURATION)
        revealGravity = a.getInt(R.styleable.RevealLayout_revealGravity, Gravity.CENTER)
        a.recycle()
    }

    fun addView(child: View?) {
        if (revealId == UNSPECIFIED_ID) return
        child?.let {
            if (it.id != revealId) return
            it.post {
                layout.reveal(it, revealGravity).apply {
                    if (revealDuration != UNSPECIFIED_DURATION) {
                        duration = revealDuration.toLong()
                    }
                }.start()
            }
        }
    }
}