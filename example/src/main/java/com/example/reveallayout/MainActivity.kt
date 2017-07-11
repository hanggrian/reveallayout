package com.example.reveallayout

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonSimple.setOnClickListener(this)
        buttonCustom.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        startActivity(Intent(this, if (v.id == R.id.buttonSimple)
            SimpleActivity::class.java
        else
            CustomActivity1::class.java))
    }
}