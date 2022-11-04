package com.icedtea.chronos

import android.provider.ContactsContract
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
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
    fun wrongCredentialLoginFail(){
        onView(withId(emailInput)).perform(typeText("enfsithi09@gmail.com"))
        onView(withId(passInput)).perform(typeText("wrongpassword"))
        val loginBtn = onView(withId(loginBtn))

        Intents.init()
        loginBtn.perform(click())
        intended(hasComponent(LoginActivity::class.java.name))
    }
    @Test
    fun successLogin(){
        val emailInput = onView(withId(emailInput))
        val passInput = onView(withId(passInput))
        val loginBtn = onView(withId(loginBtn))

        emailInput.perform(typeText("enfsithi09@gmail.com"))
        passInput.perform(typeText("wifewife"))

        loginBtn.perform(click())
        intended(hasComponent(MainActivity::class.java.name))
    }

}