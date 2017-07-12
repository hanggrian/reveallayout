package com.hendraanggrian.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.hendraanggrian.reveallayout.RevealableLayout
import com.hendraanggrian.reveallayout.RevealLayoutImpl

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
class RevealFrameLayout @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0) : io.codetail.widget.RevealFrameLayout(context, attrs, defStyle), RevealableLayout {

    internal val impl = object : RevealLayoutImpl(context, attrs) {
        override val layout: RevealableLayout get() = this@RevealFrameLayout
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        super.addView(child, index, params)
        impl.addView(child)
    }
}