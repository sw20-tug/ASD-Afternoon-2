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
class HotelDetailActivityTest {
    var hotelData: HotelData = HotelData(1, 2, "there",  R.drawable.untitled, "Hotel SUPER", 200, 20, "Nice hotel", 5.0F, 4)

    @get:Rule
    val mActivityTestRule: IntentsTestRule<HotelDetailActivity> =
        object : IntentsTestRule<HotelDetailActivity>(HotelDetailActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(targetContext, HotelDetailActivity::class.java).apply {
                    putExtra("hotelData", hotelData)
                }
            }
        }

    @Test
    fun verifyIfRatingBarIsCorrect() {
        // check if rating bar exists
        onView(withId(R.id.ratingBar)).check(matches(isDisplayed()))
        // todo check if rating is correctly set
    }

    @Test
    fun verifyIfHotelStarsAreCorrect() {
        // check if rating bar exists
        onView(withId(R.id.hotelStars)).check(matches(isDisplayed()))
        // todo check if stars are correctly set
    }
    @Test fun testPictureFragment() {
        // check if hotel pictures are displayed
        val fragment = HotelPictureFragment().apply {
            arguments = Bundle().apply {
                putParcelable(HotelDetailFragment.ARG_ITEM,
                    hotelData)
                putInt(
                    HotelDetailFragment.ARG_ITEM_ID,
                    hotelData.hotel_id
                )
            }
        }
        mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction()
            .add(R.id.hotel_picture_container, fragment).commit()
    }


@Test fun testDetailFragment() {
        // check if hotel pictures are displayed
        val fragment = HotelDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(HotelDetailFragment.ARG_ITEM,
                    hotelData)
                putInt(
                    HotelDetailFragment.ARG_ITEM_ID,
                    hotelData.hotel_id
                )
            }
        }

        mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction()
            .add(R.id.hotel_detail_container, fragment).commit()

    }

    @Test
    fun verifyIfHotelRatingOpens() {
        // verify if button is displayed on screen
        onView(withId(R.id.rating_button)).check(matches(isDisplayed()))
        // Clicks rating_button
        onView(withId(R.id.rating_button)).perform(click())

        // Verifies that the HotelRatingActivity received an intent with the correct id
        // with the correct package name and message.
        intended(allOf(
            hasComponent(hasShortClassName(".HotelRatingActivity")),
            toPackage(PACKAGE_NAME),
            hasExtra("hotelData", hotelData)))
    }

}
