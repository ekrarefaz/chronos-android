package com.icedtea.chronos

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.icedtea.chronos.view.LoginActivity
import com.icedtea.chronos.view.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    private var emailInput = 0
    private var passInput = 0
    private var loginBtn = 0

    @get:Rule
    var activityRule: ActivityScenarioRule<LoginActivity>
            = ActivityScenarioRule(LoginActivity::class.java)
    @Before
    fun initValidString() {
        // Please set your id names here.
        emailInput = R.id.email_text
        passInput = R.id.pass_text
        loginBtn = R.id.login_button
    }

    @Test
    // Testing Successful Login
    fun successLogin(){
        val emailInput = onView(withId(emailInput))
        val passInput = onView(withId(passInput))
        val loginBtn = onView(withId(loginBtn))
        Intents.init()

        emailInput.perform(typeText("ekrar.efaz@gmail.com"), closeSoftKeyboard())
        passInput.perform(typeText("masteruser"), closeSoftKeyboard())

        loginBtn.perform(click())
        Thread.sleep(1000)
        loginBtn.perform(click())
        intended(hasComponent(MainActivity::class.java.name))
    }

}