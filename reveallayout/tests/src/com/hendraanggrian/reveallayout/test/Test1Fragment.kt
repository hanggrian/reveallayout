package com.hendraanggrian.reveallayout.test

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
class Test1Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? = LayoutInflater.from(context).inflate(R.layout.fragment_test1, container, false)
}