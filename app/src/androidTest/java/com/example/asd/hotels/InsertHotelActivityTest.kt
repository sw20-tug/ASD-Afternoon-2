package com.example.asd.hotels

import android.content.Intent
import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.asd.hotels.dummy.HotelData
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.fragment.app.testing.launchFragmentInContainer as launchFragmentInContainer1


private const val PACKAGE_NAME = "com.example.asd.hotels"



@RunWith(AndroidJUnit4::class)
class InsertHotelActivityTest {

    @get:Rule
    val mActivityTestRule: IntentsTestRule<InsertHotelActivity> =
        object : IntentsTestRule<InsertHotelActivity>(InsertHotelActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(targetContext, InsertHotelActivity::class.java).apply {}
            }
        }

    @Test
    fun verifyIfComponentIsCorrect() {
        // check if components exist
        onView(withId(R.id.hotel_capacity)).check(matches(isDisplayed()))
        onView(withId(R.id.hotel_description)).check(matches(isDisplayed()))
        onView(withId(R.id.hotel_price)).check(matches(isDisplayed()))
        onView(withId(R.id.hotel_category)).check(matches(isDisplayed()))
        onView(withId(R.id.hotel_stars)).check(matches(isDisplayed()))
    }

}
