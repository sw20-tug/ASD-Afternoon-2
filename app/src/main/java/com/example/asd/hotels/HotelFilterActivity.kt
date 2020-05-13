package com.example.asd.hotels

import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_seek_bar.*

class HotelFilterActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seek_bar)

        // not relevant for release at the moment  produces error
        /*prange_seekbar.setOnRangeSeekbarChangeListener { minValue, maxValue ->
            price_range.setText("$minValue -- $maxValue")

        }*/

    }
}
