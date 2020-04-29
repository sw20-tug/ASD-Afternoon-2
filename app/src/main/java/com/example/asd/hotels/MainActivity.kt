package com.example.asd.hotels

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asd.hotels.dummy.HotelData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()*/
            val intent = Intent(this, HotelDetailActivity::class.java)
            startActivity(intent)
        }

        val hotel_values = listOf(
            HotelData(1, 1, "Vienna", R.drawable.untitled,
                "Sample Name", 500, 200,
                "Test description for vienna"),
            HotelData(2, 1, "Graz", R.drawable.untitled,
                "Sample Name", 500, 200,
                "Test description for Graz")
        )

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
