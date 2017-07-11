package com.hendraanggrian.reveallayout.test

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.ProgressBar
import io.codetail.widget.RevealFrameLayout

/**
 * @author Hendra Anggrian (hendraanggian@gmail.com)
 */
abstract class InstrumentedActivity : AppCompatActivity() {

    abstract val contentView: Int

    var toolbar: Toolbar? = null
    var progressBar: ProgressBar? = null
    var layout: RevealFrameLayout? = null

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView)
        toolbar = findViewById(R.id.toolbar) as Toolbar
        progressBar = findViewById(R.id.progressBar) as ProgressBar
        layout = findViewById(R.id.layout) as RevealFrameLayout
    }
}