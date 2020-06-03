package com.example.asd.hotels

import android.content.Intent
import android.view.View
import android.widget.RatingBar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.asd.hotels.dummy.HotelData
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


private const val PACKAGE_NAME = "com.example.asd.hotels"



@RunWith(AndroidJUnit4::class)
class HotelRatingActivityTest {
    var hotelData: HotelData = HotelData(1, 2, "there",  R.drawable.untitled, "Hotel SUPER", 200, 20, "Nice hotel", 5.0F, 4)
    var rating: Float = 3f
    @get:Rule
    val mActivityTestRule: IntentsTestRule<HotelRatingActivity> =
        object : IntentsTestRule<HotelRatingActivity>(HotelRatingActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(targetContext, HotelRatingActivity::class.java).apply {
                    putExtra("hotelData", hotelData)
                }
            }
        }

    @Test
    fun verifyIfRatingBarIsCorrect() {
        // check if rating bar exists
        onView(withId(R.id.rate_hotel)).check(matches(isDisplayed()))
    }

    @Test
    fun verifyIfDismissButtonWorks() {
        // check if button dismiss exists
        onView(withId(R.id.button_dismiss)).check(matches(isDisplayed()))
        // Clicks dismiss button
        onView(withId(R.id.button_dismiss)).perform(click())
    }

    @Test
    fun verifyIfDoneButtonWorks() {
        // check if button done exists
        onView(withId(R.id.button_done)).check(matches(isDisplayed()))
        // Clicks done button
        onView(withId(R.id.button_done)).perform(click())
    }

    fun perform(uiController: UiController?, view: View) {
        val ratingBar = view as RatingBar
        ratingBar.rating = rating
    }
    @Test
    fun verifyDoneWithRating() {
        onView(withId(R.id.rate_hotel)).perform();

        val oldRating = hotelData.hotel_rating
        val newRating = (rating + oldRating) / 2
    }

}
