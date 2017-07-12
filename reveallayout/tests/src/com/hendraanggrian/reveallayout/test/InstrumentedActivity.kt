package com.hendraanggrian.reveallayout.test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * @author Hendra Anggrian (hendraanggian@gmail.com)
 */
class InstrumentedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instrumented)
    }
}