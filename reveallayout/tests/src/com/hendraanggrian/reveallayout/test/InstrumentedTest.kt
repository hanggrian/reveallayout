package com.hendraanggrian.reveallayout.test

import android.os.Build
import android.os.CountDownTimer
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ProgressBar
import com.hendraanggrian.widget.RevealFrameLayout
import org.hamcrest.Matcher
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class InstrumentedTest {

    companion object {
        private const val DELAY_COUNTDOWN: Long = 3000
    }

    @Rule @JvmField var rule = ActivityTestRule(InstrumentedRevealByLayoutActivity::class.java)

    @Test
    fun test1_reveal_bylayout() {
        onView(withId(R.id.toolbar)).perform(setTitle("test1_reveal_bylayout()"))
        onView(withId(R.id.progressBar)).perform(delay())
    }

    @Test
    fun test2_reveal_programmatically() {
        onView(withId(R.id.toolbar)).perform(setTitle("test2_reveal_programmatically()"))
        onView(withId(R.id.layout)).perform(object : ViewAction {
            override fun getDescription(): String = "test2_reveal_programmatically()"
            override fun getConstraints(): Matcher<View> = isAssignableFrom(RevealFrameLayout::class.java)
            override fun perform(uiController: UiController?, view: View?) {
                view as RevealFrameLayout

            }
        })
        onView(withId(R.id.progressBar)).perform(delay())
    }

    fun setTitle(title: String): ViewAction = object : ViewAction {
        override fun getDescription() = "set title to " + title
        override fun getConstraints() = isAssignableFrom(Toolbar::class.java)
        override fun perform(uiController: UiController, view: View) {
            (view as Toolbar).title = title
        }
    }

    fun delay(): ViewAction = object : ViewAction {
        override fun getDescription() = "delay for " + DELAY_COUNTDOWN
        override fun getConstraints() = isAssignableFrom(ProgressBar::class.java)
        override fun perform(uiController: UiController, view: View) {
            view as ProgressBar
            object : CountDownTimer(DELAY_COUNTDOWN, 100) {
                override fun onTick(millisUntilFinished: Long) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        view.setProgress((view.max * millisUntilFinished / DELAY_COUNTDOWN).toInt(), true)
                    } else {
                        view.progress = (view.max * millisUntilFinished / DELAY_COUNTDOWN).toInt()
                    }
                }

                override fun onFinish() {
                    view.visibility = View.GONE
                }
            }.start()
            uiController.loopMainThreadForAtLeast(DELAY_COUNTDOWN)
        }
    }
}