package com.example.asd.hotels

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.test.core.app.ApplicationProvider
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
import com.example.asd.hotels.provider.DatabaseProvider
import junit.framework.Assert.assertEquals
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.fragment.app.testing.launchFragmentInContainer as launchFragmentInContainer1

private const val PACKAGE_NAME = "com.example.asd.hotels"

class DatabaseTest {
    @Test
    fun testDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        val testDatabaseProvider: DatabaseProvider = DatabaseProvider(context)
        val test_location_id = testDatabaseProvider.insert_location("Bielefeld")
        var id = -1;
        if (test_location_id != null) {
            id = test_location_id.toInt()
        }
        val test_location_name = testDatabaseProvider.get_location(id)

        assertEquals("Bielefeld", test_location_name)
    }
}