package com.example.reveallayout

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_custom1.*

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
class CustomActivity1 : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom1)
        buttonSimple.setOnClickListener(this)
        buttonFrom.setOnClickListener(this)
        buttonFull.setOnClickListener(this)
        maskSimple.setOnClickListener(this)
        maskTo.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.buttonSimple -> layout.reveal(maskSimple).apply {
                duration = 2000
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator) {
                        maskSimple.visibility = View.VISIBLE
                    }
                })
                start()
            }
            R.id.maskSimple -> layout.reveal(maskSimple, true).apply {
                duration = 2000
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        maskSimple.visibility = View.INVISIBLE
                    }
                })
                start()
            }
            R.id.buttonFrom -> AnimatorSet().apply {
                duration = 2000
                interpolator = FastOutSlowInInterpolator()
                playTogether(layout.revealTo(buttonFrom, maskTo))
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator) {
                        buttonFrom.visibility = View.INVISIBLE
                        maskTo.visibility = View.VISIBLE
                    }
                })
                start()
            }
            R.id.maskTo -> AnimatorSet().apply {
                duration = 2000
                playTogether(layout.revealTo(buttonFrom, maskTo, true))
                interpolator = FastOutSlowInInterpolator()
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        buttonFrom.visibility = View.VISIBLE
                        maskTo.visibility = View.INVISIBLE
                    }
                })
                start()
            }
            R.id.buttonFull -> Intent(this, CustomActivity2::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                putExtra(CustomActivity2.EXTRA_RECT, createRect(buttonFull.parent as ViewGroup, buttonFull))
                startActivity(this)
            }
        }
    }

    private fun createRect(parent: ViewGroup, view: View): Rect {
        val rect = Rect()
        view.getDrawingRect(rect)
        parent.offsetDescendantRectToMyCoords(view, rect)
        return rect
    }
}