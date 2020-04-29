package com.example.asd.hotels

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asd.hotels.dummy.HotelData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_main_sorted.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)

        val hotel_values = listOf(
            HotelData(1, 1, 1, "Beta", 1, 3, "1", 1),
            HotelData(2, 2, 1, "Alpha", 1, 2, "1", 1),
            HotelData(3, 1, 1, "Echo", 1, 7, "1", 1),
            HotelData(4, 1, 1, "Oscar", 1, 14, "1", 1),
            HotelData(5, 1, 1, "Sierra", 1, 9, "1", 1),
            HotelData(6, 1, 1, "Golf", 1, 1, "1", 1),
            HotelData(7, 1, 1, "Charlie", 1, 2, "1", 1),
            HotelData(8, 1, 1, "Juliet", 1, 3, "1", 1),
            HotelData(9, 1, 1, "Tango", 1, 4, "1", 1)
        )


        var overViewVisible = true
        sort_button_price.setOnClickListener {

            //add fragment to fill sorting view
            //supportFragmentManager.beginTransaction()
            //    .add(R.id.SortingView, MainFragment.newInstance(), "hotelList").commit()


            var hotelDummyListSorted = hotel_values.sortedBy { it.price }

            SortingView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = SortingViewAdapter(hotelDummyListSorted)
            }

            if (overViewVisible) {
                OverView.setVisibility(View.INVISIBLE)
                SortingView.setVisibility(View.VISIBLE)
                overViewVisible = false
            } else {
                OverView.setVisibility(View.VISIBLE)
                SortingView.setVisibility(View.GONE)
                overViewVisible = true
            }
        }

        // Setting up the adapter
        OverView.apply {
            // Set up the layer
            layoutManager = LinearLayoutManager(this@MainActivity)
            // Pass the list into OverViewAdapter
            adapter = OverViewAdapter(hotel_values) {
                d("MainActivity","Hi from main")
                // Call the detail view.
                startActivity(Intent(this@MainActivity,
                    HotelDetailActivity::class.java))
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

//    class Hotel(){
//        var hotel_price;
//    }

//    public fun inPriceRange(var minPrice:Int, var maxPrice:Int, var hotel:Hotel):Boolean{
//        if((hotel.hotel_price<=maxPrice) && (hotel.hotel_price>=minPrice))
//            return true;
//        else
//            return false;
//    }

    */

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
