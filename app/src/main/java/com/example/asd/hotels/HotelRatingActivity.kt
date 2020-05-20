package com.example.asd.hotels

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import kotlinx.android.synthetic.main.hotel_detail.*

class HotelRatingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_rating)
        val hotelId=intent.getIntExtra("hotel_id", -1)

        val done = findViewById<Button>(R.id.button_done)
        val dismiss = findViewById<Button>(R.id.button_dismiss)
        val rating_bar = findViewById<RatingBar>(R.id.rate_hotel)

        //Button done clicked:
        done.setOnClickListener {view -> doneWithRating() }

        //Button dismiss clicked
        dismiss.setOnClickListener {view -> dismissView()}
    }

    fun doneWithRating(){
        //new comment in DB erstellen -> comment abspeichern

        //rating abspeichern und gesamtbewertung neu berechnen

        //zurück zum detailview gehen
        finish();
        super.onBackPressed();
    }

    fun dismissView() {
        //zurück zum detailview gehen
        finish();
        super.onBackPressed();
    }
}
