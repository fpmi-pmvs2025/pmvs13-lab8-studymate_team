package com.bsu.studymate.ui

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bsu.studymate.R
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScheduleActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(ScheduleActivity::class.java)

    @Before
    fun setup() {
        val prefs = ApplicationProvider.getApplicationContext<Context>()
            .getSharedPreferences("auth", Context.MODE_PRIVATE)
        prefs.edit()
            .putLong("user_id", 1L)
            .putInt("group_no", 1)
            .putString("first_name", "Иван")
            .putString("last_name", "Иванов")
            .apply()
    }

    @Test
    fun testScheduleScreenElements() {
        onView(withId(R.id.toolbar))
            .check(matches(isDisplayed()))

        onView(withId(R.id.spinnerDay))
            .check(matches(isDisplayed()))

        onView(withId(R.id.recycler))
            .check(matches(isDisplayed()))

        onView(withId(R.id.btnLogout))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testDaySpinnerSelection() {
        onView(withId(R.id.spinnerDay))
            .perform(click())

        onView(withText("Вторник"))
            .perform(click())

        onView(allOf(
            isDescendantOfA(withId(R.id.spinnerDay)),
            withText("Вторник")
        )).check(matches(isDisplayed()))
    }

    @Test
    fun testLogout() {
        onView(withId(R.id.btnLogout))
            .perform(click())

        onView(withId(R.id.etEmail))
            .check(matches(isDisplayed()))
    }
} 