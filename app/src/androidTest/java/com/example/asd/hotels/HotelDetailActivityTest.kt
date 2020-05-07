package com.example.asd.hotels

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private const val PACKAGE_NAME = "com.example.hotels"


@RunWith(AndroidJUnit4::class)
class HotelDetailActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule<HotelDetailActivity>(
        HotelDetailActivity::class.java
    )

    private var hotel_id = 1

    @Before
    fun initValidId() { // Specify a valid id.
        hotel_id = 1
    }

    @Test
    fun verifyIfHotelRatingOpens() {
        onView(withId(R.id.rating_button)).check(matches(isDisplayed()))

        // Clicks rating_button
        onView(withId(R.id.rating_button)).perform(click())

        /* TODO
        // Verifies that the HotelRatingActivity received an intent
        // with the correct package name and message.
        intended(allOf(
            hasComponent(hasShortClassName(".HotelRatingActivity")),
            toPackage(PACKAGE_NAME),
            hasExtra("hotel_id", hotel_id)))*/
    }

}