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
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(LoginActivity::class.java)

    @Before
    fun setup() {
        // Clear SharedPreferences to prevent auto-login
        val prefs = ApplicationProvider.getApplicationContext<Context>()
            .getSharedPreferences("auth", Context.MODE_PRIVATE)
        prefs.edit().clear().apply()
    }

    @Test
    fun testLoginScreenElements() {
        onView(withId(R.id.etEmail))
            .check(matches(isDisplayed()))
            .check(matches(withHint(R.string.email)))

        onView(withId(R.id.etPassword))
            .check(matches(isDisplayed()))
            .check(matches(withHint(R.string.password)))

        onView(withId(R.id.btnLogin))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.login)))
    }

    @Test
    fun testInvalidLogin() {
        onView(withId(R.id.etEmail))
            .perform(typeText("invalid@email.com"), closeSoftKeyboard())
        
        onView(withId(R.id.etPassword))
            .perform(typeText("wrongpassword"), closeSoftKeyboard())

        onView(withId(R.id.btnLogin))
            .perform(click())

        onView(withText("Неверные данные"))
            .check(matches(isDisplayed()))
    }
} 