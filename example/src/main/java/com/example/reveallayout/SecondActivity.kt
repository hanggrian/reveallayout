package com.example.reveallayout

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.hendraanggrian.bundler.BindExtra
import com.hendraanggrian.bundler.Bundler
import com.hendraanggrian.kota.content.getColor
import com.hendraanggrian.reveallayout.Radius
import kotlinx.android.synthetic.main.activity_second.*

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
class SecondActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_RECT = "com.example.circularreveal.CustomActivity2"
    }

    @BindExtra(EXTRA_RECT) lateinit var rect: Rect

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        setSupportActionBar(toolbar)
        Bundler.bindExtras(this)
        layout.post {
            revealLayout.reveal(layout, rect.centerX(), rect.centerY(), Radius.GONE_ACTIVITY).apply {
                duration = 1000
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        if (Build.VERSION.SDK_INT >= 21) {
                            window!!.statusBarColor = theme.getColor(R.attr.colorAccent, true)
                        }
                    }
                })
            }.start()
        }
    }

    override fun onBackPressed() {
        revealLayout.reveal(layout, rect.centerX(), rect.centerY(), Radius.ACTIVITY_GONE).apply {
            duration = 1000
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator?) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        window!!.statusBarColor = theme.getColor(R.attr.colorPrimaryDark, true)
                    }
                }

                override fun onAnimationEnd(animation: Animator) {
                    layout.visibility = View.INVISIBLE
                    finish()
                    overridePendingTransition(0, 0)
                }
            })
        }.start()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}