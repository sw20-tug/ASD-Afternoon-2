package com.example.asd.hotels

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_seek_bar.*

class SeekBarActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seek_bar)

        prange_seekbar.setOnRangeSeekbarChangeListener { minValue, maxValue ->
            price_range.setText("$minValue -- $maxValue")

        }

    }
}
