package de.mfietz.expenses.myexpenses

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import de.mfietz.expenses.myexpenses.view.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

@LargeTest
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun clickFab() {
        onView(withId(R.id.add_expense)).perform(click())
        onView(withText("foo")).check(matches(isDisplayed()))
    }

}
