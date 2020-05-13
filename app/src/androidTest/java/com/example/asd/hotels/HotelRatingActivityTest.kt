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

@RunWith(AndroidJUnit4::class)
class HotelRatingActivityTest {
    @Rule @JvmField
    var activityRule = IntentsTestRule<HotelRatingActivity>(
        HotelRatingActivity::class.java
    )

    private var hotel_id = -1

    @Test
    fun checkDismissBtn(){
        onView(withId(R.id.button_dismiss)).check(matches(isDisplayed()))
        onView(withId(R.id.button_dismiss)).perform(click())

        intended(allOf(
            hasComponent(hasShortClassName(".HotelDetailActivity")),
            toPackage(PACKAGE_NAME),
            hasExtra("hotel_id", hotel_id)))
    }

    //TODO super.onBackPressed() verwenden
}