package com.hendraanggrian.reveallayout.test

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hendraanggrian.kota.view.findViewBy
import com.hendraanggrian.reveallayout.Radius
import com.hendraanggrian.widget.RevealFrameLayout

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
class Test2Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? = LayoutInflater.from(context).inflate(R.layout.fragment_test2, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val layout = view.findViewBy<RevealFrameLayout>(R.id.layout)
        val card = view.findViewBy<View>(R.id.card)
        card.setOnClickListener {
            layout.reveal(card, Gravity.CENTER, Radius.DEFAULT_GONE).apply {
                duration = 2000
            }.start()
        }
        card.post {
            layout.reveal(card).apply {
                duration = 2000
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        card.performClick()
                    }
                })
            }.start()
        }
    }
}