package com.example.asd.hotels

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import com.example.asd.hotels.dummy.HotelData
import kotlinx.android.synthetic.main.activity_hotel_rating.*
import kotlinx.android.synthetic.main.hotel_detail.*

class HotelRatingActivity : AppCompatActivity() {
    private lateinit var hotelData: HotelData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_rating)

        hotelData = intent.getParcelableExtra("hotelData")

        val done = findViewById<Button>(R.id.button_done)
        val dismiss = findViewById<Button>(R.id.button_dismiss)
        val naming = findViewById<TextView>(R.id.hotel_name)

        //Button done clicked:
        done.setOnClickListener {view -> doneWithRating() }

        //Button dismiss clicked
        dismiss.setOnClickListener {view -> dismissView()}

        if (savedInstanceState == null) {
            naming.text = hotelData.hotel_name
        }
    }



    fun doneWithRating(){

        val barRating = findViewById<RatingBar>(R.id.rate_hotel).numStars
        val oldRating = hotelData.hotel_rating
        val newRating = (barRating + oldRating) / 2
        hotelData.hotel_rating = newRating

        //zurück zum detailview gehen
        super.onBackPressed();
    }

    fun dismissView() {
        //zurück zum detailview gehen
        super.onBackPressed();
    }
}
