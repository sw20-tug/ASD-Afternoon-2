package com.example.asd.hotels

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.*

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_seek_bar.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)



        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        // Example of a call to a native method
        sample_text.text = stringFromJNI()
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

    class Hotel(){
        var hotel_price = 0;
        var location_id = 0;
    }

    fun inPriceRange(minPrice:Int, maxPrice:Int, hotel:Hotel):Boolean{
        var statePriceR:Boolean = false;
        if((hotel.hotel_price<=maxPrice) && (hotel.hotel_price>=minPrice))
            statePriceR = true;
        return statePriceR;
    }
    fun filterbyPrice(hotelsToFilter:MutableList<Hotel>){
        hotelsToFilter.forEach { hotel_inList->
            //minPrice, maxPrice form RangeSeekBar
            if(!inPriceRange(0, 100, hotel_inList))
                hotelsToFilter.remove(hotel_inList);
        }
    }

    fun inLocation(location_id:Int, hotel:Hotel):Boolean{
        var stateLocation:Boolean = false;
        if(hotel.location_id== location_id)
            stateLocation = true;
        return stateLocation;
    }
    fun filterbyLocation(){

    }




    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
