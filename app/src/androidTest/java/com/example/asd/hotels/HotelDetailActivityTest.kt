package com.example.asd.hotels

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
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private const val PACKAGE_NAME = "com.example.asd.hotels"

private const val PACKAGE_NAME = "com.example.asd.hotels"


@RunWith(AndroidJUnit4::class)
class HotelDetailActivityTest {
    @Rule
    @JvmField
    var activityRule = IntentsTestRule<HotelDetailActivity>(
        HotelDetailActivity::class.java
    )

    private var hotel_id = -1

    @Test
    fun verifyIfHotelRatingOpens() {
        // verify if button is displayed on screen
        onView(withId(R.id.rating_button)).check(matches(isDisplayed()))
        // Clicks rating_button
        onView(withId(R.id.rating_button)).perform(click())

        // Verifies that the HotelRatingActivity received an intent
        // with the correct package name and message.
        intended(allOf(
            hasComponent(hasShortClassName(".HotelRatingActivity")),
            toPackage(PACKAGE_NAME),
            hasExtra("hotel_id", hotel_id)))
    }

}