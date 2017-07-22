package com.hendraanggrian.reveallayout.test

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hendraanggrian.kota.view.findViewBy
import com.hendraanggrian.reveallayout.Radius
import com.hendraanggrian.widget.RevealFrameLayout

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
class Test3Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? = LayoutInflater.from(context).inflate(R.layout.fragment_test3, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val layout = view.findViewBy<RevealFrameLayout>(R.id.layout)
        val card = view.findViewBy<CardView>(R.id.card)
        val button = view.findViewBy<FloatingActionButton>(R.id.button)
        card.setOnClickListener {
            AnimatorSet().apply {
                duration = 2000
                playTogether(layout.revealTo(button, card, Radius.DEFAULT_GONE))
                interpolator = FastOutSlowInInterpolator()
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        button.visibility = View.VISIBLE
                        card.visibility = View.INVISIBLE
                    }
                })
                start()
            }
        }
        card.post {
            AnimatorSet().apply {
                duration = 2000
                interpolator = FastOutSlowInInterpolator()
                playTogether(layout.revealTo(button, card))
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator) {
                        button.visibility = View.INVISIBLE
                        card.visibility = View.VISIBLE
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        card.performClick()
                    }
                })
                start()
            }
        }
    }
}