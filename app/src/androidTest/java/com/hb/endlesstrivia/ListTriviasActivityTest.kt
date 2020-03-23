package com.hb.endlesstrivia


import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.hb.endlesstrivia.ui.list_trivia.ListTriviasActivity
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class ListTriviasActivityTest {

    @get:Rule
    var listTriviaActivityTestRule = ActivityScenarioRule(ListTriviasActivity::class.java)

    @Test
    fun testUiElements() {
        Espresso.onView(ViewMatchers.withId(R.id.recycler_list_trivia))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}
