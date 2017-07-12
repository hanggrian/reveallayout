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
import android.support.v4.app.Fragment
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
@RunWith(AndroidJUnit4::class)
class InstrumentedTest {

    @Rule @JvmField var rule = ActivityTestRule(InstrumentedActivity::class.java)

    @Test
    @Throws(Exception::class)
    fun revealSimple() {
        onView(withId(R.id.container)).perform(replaceFragment(Test1Fragment()))
        onView(withId(R.id.progressBar)).perform(delay(2000))
    }

    @Test
    @Throws(Exception::class)
    fun revealProgramatically() {
        onView(withId(R.id.container)).perform(replaceFragment(Test2Fragment()))
        onView(withId(R.id.progressBar)).perform(delay(4000))
    }

    @Test
    @Throws(Exception::class)
    fun revealTo() {
        onView(withId(R.id.container)).perform(replaceFragment(Test3Fragment()))
        onView(withId(R.id.progressBar)).perform(delay(2000))
    }

    fun replaceFragment(fragment: Fragment): ViewAction = object : ViewAction {
        override fun getDescription() = "attaching fragment " + fragment.javaClass.simpleName
        override fun getConstraints() = isAssignableFrom(FrameLayout::class.java)
        override fun perform(uiController: UiController?, view: View) {
            rule.activity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit()
        }
    }

    fun delay(millis: Long): ViewAction = object : ViewAction {
        override fun getDescription() = "delay for " + millis
        override fun getConstraints() = isAssignableFrom(ProgressBar::class.java)
        override fun perform(uiController: UiController, view: View) {
            view as ProgressBar
            object : CountDownTimer(millis, 100) {
                override fun onTick(millisUntilFinished: Long) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        view.setProgress((view.max * millisUntilFinished / millis).toInt(), true)
                    } else {
                        view.progress = (view.max * millisUntilFinished / millis).toInt()
                    }
                }

                override fun onFinish() {
                    view.visibility = View.GONE
                }
            }.start()
            uiController.loopMainThreadForAtLeast(millis)
        }
    }
}