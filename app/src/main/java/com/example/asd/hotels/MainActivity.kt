package com.example.asd.hotels

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.util.Log.d
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asd.hotels.dummy.HotelData
import com.example.asd.hotels.provider.DatabaseProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_main_sorted.*
import java.io.File
import java.io.InputStream
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val hotelValues = mutableListOf<HotelData>()

        var nameList = resources.getStringArray(R.array.nameList)

        try {
            val connectMySql = DatabaseProvider(this)

            connectMySql.insert_location("Alpha")
            connectMySql.insert_location("Beta")
            connectMySql.insert_location("Charlie")
            connectMySql.insert_location("Delta")
            connectMySql.insert_location("Echo")
            connectMySql.insert_location("Foxtrot")
            connectMySql.insert_location("Golf")
            connectMySql.insert_location("Hotel")
            connectMySql.insert_location("India")

            for (x in 0 until 10) {
                connectMySql.insert_category("Test")
                connectMySql.insert_photo(R.drawable.untitled)
            }

            for (x in 0 until 10) {
                connectMySql.insert_hotel(
                    1,
                    x + 1,
                    "Hotel " + nameList[Random.nextInt(0, nameList.size - 1)],
                    Random.nextInt(1, 100),
                    Random.nextInt(1, 100),
                    "Gutes Hotel!",
                    (Random.nextInt(0, 9) + Random.nextFloat()),
                    Random.nextInt(1, 5),
                    x + 1
                )
            }

            for (x in 1 until 11) {
                hotelValues.add(connectMySql.get_hotels(x))
            }
        } catch (e: Exception) {
            d("MainActivity Db error", e.message)
        }

        var overViewVisible = true
        var sortedBy = SortingViewAdapter.SortEnum.UNSORTED
        sort_button_price.setOnClickListener {
            sort_button_rating.isChecked = false
            sort_button_stars.isChecked = false
            SortingView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = SortingViewAdapter(hotelValues, SortingViewAdapter.SortEnum.PRICE)
            }
            overViewVisible = if (overViewVisible) {
                OverView.setVisibility(View.INVISIBLE)
                SortingView.setVisibility(View.VISIBLE)
                sortedBy = SortingViewAdapter.SortEnum.PRICE
                false
            } else {
                if (sortedBy == SortingViewAdapter.SortEnum.PRICE) {
                    OverView.setVisibility(View.VISIBLE)
                    SortingView.setVisibility(View.GONE)
                    sortedBy = SortingViewAdapter.SortEnum.UNSORTED
                    true
                } else {
                    sortedBy = SortingViewAdapter.SortEnum.PRICE
                    false
                }
            }
        }
        sort_button_rating.setOnClickListener {
            sort_button_price.isChecked = false
            sort_button_stars.isChecked = false
            SortingView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = SortingViewAdapter(hotelValues, SortingViewAdapter.SortEnum.RATING)
            }
            overViewVisible = if (overViewVisible) {
                OverView.setVisibility(View.INVISIBLE)
                SortingView.setVisibility(View.VISIBLE)
                sortedBy = SortingViewAdapter.SortEnum.RATING
                false
            } else {
                if (sortedBy == SortingViewAdapter.SortEnum.RATING) {
                    OverView.setVisibility(View.VISIBLE)
                    SortingView.setVisibility(View.GONE)
                    sortedBy = SortingViewAdapter.SortEnum.UNSORTED
                    true
                } else {
                    sortedBy = SortingViewAdapter.SortEnum.RATING
                    false
                }
            }
        }
        sort_button_stars.setOnClickListener {
            sort_button_price.isChecked = false
            sort_button_rating.isChecked = false
            SortingView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = SortingViewAdapter(hotelValues, SortingViewAdapter.SortEnum.STARS)
            }
            overViewVisible = if (overViewVisible) {
                OverView.setVisibility(View.INVISIBLE)
                SortingView.setVisibility(View.VISIBLE)
                sortedBy = SortingViewAdapter.SortEnum.STARS
                false
            } else {
                if (sortedBy == SortingViewAdapter.SortEnum.STARS) {
                    OverView.setVisibility(View.VISIBLE)
                    SortingView.setVisibility(View.GONE)
                    sortedBy = SortingViewAdapter.SortEnum.UNSORTED
                    true
                } else {
                    sortedBy = SortingViewAdapter.SortEnum.STARS
                    false
                }
            }
        }


        // Setting up the adapter
        OverView.apply {
            // Set up the layer
            layoutManager = LinearLayoutManager(this@MainActivity)
            // Pass the list into OverViewAdapter
            adapter = OverViewAdapter(hotelValues) {
                d("MainActivity", "Hi from main")
                // Call the detail view.
                startActivity(
                    Intent(
                        this@MainActivity,
                        HotelDetailActivity::class.java
                    )
                )
            }
        }

        // Add item separator to the overview.
        val itemDecor = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        OverView.addItemDecoration(itemDecor)

        // Example of a call to a native method
//        sample_text.text = stringFromJNI()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    class Hotel() {
        var hotel_price = 0;
        var location_id = 0;
    }

    fun inPriceRange(minPrice: Int, maxPrice: Int, hotel: Hotel): Boolean {
        var statePriceR: Boolean = false;
        if ((hotel.hotel_price <= maxPrice) && (hotel.hotel_price >= minPrice))
            statePriceR = true;
        return statePriceR;
    }

    fun filterbyPrice(hotelsToFilter: MutableList<Hotel>) {
        hotelsToFilter.forEach { hotel_inList ->
            //minPrice, maxPrice form RangeSeekBar
            if (!inPriceRange(0, 100, hotel_inList))
                hotelsToFilter.remove(hotel_inList);
        }
    }

    fun inLocation(location_id: Int, hotel: Hotel): Boolean {
        var stateLocation: Boolean = false;
        if (hotel.location_id == location_id)
            stateLocation = true;
        return stateLocation;
    }

    fun filterbyLocation() {

    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
//    external fun stringFromJNI(): String
//
//    companion object {
//
//        // Used to load the 'native-lib' library on application startup.
//        init {
//            System.loadLibrary("native-lib")
//        }
//    }
}

