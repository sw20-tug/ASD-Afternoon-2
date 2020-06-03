package com.example.asd.hotels

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.asd.hotels.dummy.HotelData
import kotlinx.android.synthetic.main.activity_seek_bar.*

class HotelFilterActivity() : AppCompatActivity() {

    private lateinit var hotelIsListed: ArrayList<HotelData>


    var seekb_minPrice = 0
    var seekb_maxPrice = 100
    fun inPriceRange(minPrice: Int, maxPrice: Int, hotel: HotelData): Boolean {
        var statePriceR: Boolean = false;
        if ((hotel.price <= maxPrice) && (hotel.price >= minPrice))
            statePriceR = true;
        return statePriceR;
    }

    fun filterbyPrice(minimumPrice:Int, maximumPrice:Int, hotelsToFilter: ArrayList<HotelData>) : List<HotelData>{
        var hotelsFiltered:ArrayList<HotelData>
        hotelsFiltered= hotelsToFilter.filter{ it.price < 3} as ArrayList<HotelData>
        /*for(hotel_inList in hotelsToFilter) {
            //minPrice, maxPrice from RangeSeekBar
            if (!inPriceRange(minimumPrice, maximumPrice, hotel_inList))
                hotelsToFilter.remove(hotel_inList);

        }*/
        return hotelsFiltered
    }

    fun inLocation(location_id: String, hotel: HotelData): Boolean {
        var stateLocation: Boolean = false;
        if (hotel.location == location_id)
            stateLocation = true;
        return stateLocation;
    }

    fun filterbyLocation() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seek_bar)

        hotelIsListed = intent.getParcelableArrayListExtra("hotelData")

        seekbar_prange.setOnRangeSeekbarChangeListener { minValue, maxValue ->
            txt_price_range.setText("$minValue -- $maxValue")
            seekb_minPrice = minValue.toInt()
            seekb_maxPrice = maxValue.toInt()
        }
        btn_filter.setOnClickListener {
            filterbyPrice(seekb_minPrice,seekb_maxPrice, hotelIsListed)
        }

    }


}
