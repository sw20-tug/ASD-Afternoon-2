package com.example.asd.hotels

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
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
        val rating_bar = findViewById<RatingBar>(R.id.rate_hotel)

        //Button done clicked:
        done.setOnClickListener {view -> doneWithRating() }

        //Button dismiss clicked
        dismiss.setOnClickListener {view -> dismissView()}

        if (savedInstanceState == null) {
            val hotel_rating_fragment = HotelRatingFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(HotelRatingFragment.ARG_ITEM, hotelData)
                    putInt(
                        HotelDetailFragment.ARG_ITEM_ID,
                        hotelData.hotel_id
                    )
                }
            }
            supportFragmentManager.beginTransaction()
                .add(R.id.hotel_picture_container_ratingview, hotel_rating_fragment)
                .commit()
        }
    }



    fun doneWithRating(){
        //new comment in DB erstellen -> comment abspeichern

        //rating abspeichern und gesamtbewertung neu berechnen

        //zurück zum detailview gehen
        super.onBackPressed();
    }

    fun dismissView() {
        //zurück zum detailview gehen
        super.onBackPressed();
    }
}
