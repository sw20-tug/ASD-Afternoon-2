package com.example.asd.hotels

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.asd.hotels.dummy.HotelData
import org.junit.Assert.assertEquals
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class SortingViewAdapterTest {

    private lateinit var adapter: SortingViewAdapter
    private val hotelSortedStars = mutableListOf<HotelData>()
    private val hotelSortedRating = mutableListOf<HotelData>()
    private val hotelSortedPrice = mutableListOf<HotelData>()
    private val hotelUnsorted = mutableListOf<HotelData>()

    @Before
    fun setUp() {
        hotelUnsorted.add(
            HotelData(
                1,
                1,
                "Test",
                R.drawable.untitled,
                "Test1",
                1,
                1,
                "Gutes Hotel",
                3,
                1
            )
        )
        hotelUnsorted.add(
            HotelData(
                1,
                1,
                "Test",
                R.drawable.untitled,
                "Test2",
                1,
                2,
                "Gutes Hotel",
                2,
                3
            )
        )
        hotelUnsorted.add(
            HotelData(
                1,
                1,
                "Test",
                R.drawable.untitled,
                "Test3",
                1,
                3,
                "Gutes Hotel",
                1,
                2
            )
        )
        hotelUnsorted.add(
            HotelData(
                1,
                1,
                "Test",
                R.drawable.untitled,
                "Test4",
                1,
                1,
                "Gutes Hotel",
                2,
                2
            )
        )
        adapter = SortingViewAdapter(hotelUnsorted)



        hotelSortedStars.add(
            HotelData(
                1,
                1,
                "Test",
                R.drawable.untitled,
                "Test1",
                1,
                1,
                "Gutes Hotel",
                3,
                1
            )
        )
        hotelSortedStars.add(
            HotelData(
                1,
                1,
                "Test",
                R.drawable.untitled,
                "Test3",
                1,
                3,
                "Gutes Hotel",
                1,
                2
            )
        )
        hotelSortedStars.add(
            HotelData(
                1,
                1,
                "Test",
                R.drawable.untitled,
                "Test4",
                1,
                1,
                "Gutes Hotel",
                2,
                2
            )
        )
        hotelSortedStars.add(
            HotelData(
                1,
                1,
                "Test",
                R.drawable.untitled,
                "Test2",
                1,
                2,
                "Gutes Hotel",
                2,
                3
            )
        )



        hotelSortedRating.add(
            HotelData(
                1,
                1,
                "Test",
                R.drawable.untitled,
                "Test3",
                1,
                3,
                "Gutes Hotel",
                1,
                2
            )
        )
        hotelSortedRating.add(
            HotelData(
                1,
                1,
                "Test",
                R.drawable.untitled,
                "Test2",
                1,
                2,
                "Gutes Hotel",
                2,
                3
            )
        )
        hotelSortedRating.add(
            HotelData(
                1,
                1,
                "Test",
                R.drawable.untitled,
                "Test4",
                1,
                1,
                "Gutes Hotel",
                2,
                2
            )
        )

        hotelSortedRating.add(
            HotelData(
                1,
                1,
                "Test",
                R.drawable.untitled,
                "Test1",
                1,
                1,
                "Gutes Hotel",
                3,
                1
            )
        )



        hotelSortedPrice.add(
            HotelData(
                1,
                1,
                "Test",
                R.drawable.untitled,
                "Test1",
                1,
                1,
                "Gutes Hotel",
                3,
                1
            )
        )
        hotelSortedPrice.add(
            HotelData(
                1,
                1,
                "Test",
                R.drawable.untitled,
                "Test4",
                1,
                1,
                "Gutes Hotel",
                2,
                2
            )
        )
        hotelSortedPrice.add(
            HotelData(
                1,
                1,
                "Test",
                R.drawable.untitled,
                "Test2",
                1,
                2,
                "Gutes Hotel",
                2,
                3
            )
        )
        hotelSortedPrice.add(
            HotelData(
                1,
                1,
                "Test",
                R.drawable.untitled,
                "Test3",
                1,
                3,
                "Gutes Hotel",
                1,
                2
            )
        )
    }

    @Test
    fun checkSortingStars() {
        adapter.setSortByValue(SortingViewAdapter.SortEnum.STARS)
        assertEquals(hotelSortedStars, adapter.sortData(hotelUnsorted))
    }

    @Test
    fun checkSortingRating() {
        adapter.setSortByValue(SortingViewAdapter.SortEnum.RATING)
        assertEquals(hotelSortedRating, adapter.sortData(hotelUnsorted))
    }

    @Test
    fun checkSortingPrice() {
        adapter.setSortByValue(SortingViewAdapter.SortEnum.PRICE)
        assertEquals(hotelSortedPrice, adapter.sortData(hotelUnsorted))
    }


}