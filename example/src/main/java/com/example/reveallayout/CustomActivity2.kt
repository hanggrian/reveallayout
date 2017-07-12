package com.example.reveallayout

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.hendraanggrian.reveallayout.Radius
import kotlinx.android.synthetic.main.activity_custom2.*

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
class CustomActivity2 : AppCompatActivity() {

    companion object {
        val EXTRA_RECT = "com.example.circularreveal.CustomActivity2"
    }

    private var rect: Rect? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom2)
        rect = intent.getParcelableExtra<Rect>(EXTRA_RECT)
        textView.post {
            layout.reveal(textView, rect!!.centerX(), rect!!.centerY(), Radius.NONE_LARGE)
                    .start()
        }
    }

    override fun onBackPressed() {
        layout.reveal(textView, rect!!.centerX(), rect!!.centerY(), Radius.LARGE_NONE).apply {
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    textView.visibility = View.INVISIBLE
                    finish()
                    overridePendingTransition(0, 0)
                }
            })
            start()
        }
    }
}