package com.example.reveallayout

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            Intent(this, SecondActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                putExtra(SecondActivity.EXTRA_RECT, createRect(button))
                startActivity(this)
            }
        }
    }

    private fun createRect(view: View) = Rect().apply {
        view.getDrawingRect(this)
        (view.parent as ViewGroup).offsetDescendantRectToMyCoords(view, this)
    }
}