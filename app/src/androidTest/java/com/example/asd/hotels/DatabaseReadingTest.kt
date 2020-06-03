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


@Test fun testDatabase() {
    val testDatabaseProvider : DatabaseProvider
    testDatabaseProvider.insert_hotel(1, 1, "Overlook Hotel", 237, 0, "Take care.", 3.0f, 7, 1)

    val realHotelData = testDatabaseProvider.get_hotels(1)
    val expectedHotelData = HotelData(1, 1, 1, 1, "Overlook Hotel", 237, 0, "Take care.", 3.0f, 7)

    assertEquals(expectedHotelData, realHotelData)
}
