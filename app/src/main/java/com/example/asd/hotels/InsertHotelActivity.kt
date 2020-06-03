package com.example.asd.hotels

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import com.example.asd.hotels.provider.DatabaseProvider
import kotlin.random.Random

class InsertHotelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
{
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_insert_hotel)

   val saveButton = findViewById<Button>(R.id.save_hotel)
    saveButton.setOnClickListener{ view -> /*addRating()*/
        try {
            val connectMySql = DatabaseProvider(this)

            var name = ""
            var price = 0
            var capacity = 0
            var description = ""
            var rating = 0.0F
            var stars = 0

            val nameField: EditText? = findViewById<EditText>(R.id.hotel_name)
            if (nameField != null) {
                name = nameField.text.toString()
            }
            var priceField: EditText? = findViewById<EditText>(R.id.hotel_price)
            if (priceField != null) {
                price = priceField.text.toString().toInt()
            }
            var descriptionField: EditText? = findViewById<EditText>(R.id.hotel_description)
            if (descriptionField != null) {
                description = descriptionField.text.toString()
            }
            val hotelStars: RatingBar = findViewById<RatingBar>(R.id.hotel_stars)
            if (hotelStars != null) {
                stars = hotelStars.rating.toInt()
            }
            var capacityField: EditText? = findViewById<EditText>(R.id.hotel_capacity)
            if (capacityField != null) {
                capacity = capacityField.text.toString().toInt()
            }
            connectMySql.insert_hotel(
                1, 1,
                name,capacity, price, description, rating, stars, 1
            )
        } catch (e: Exception) {
            Log.d("MainActivity Db error", e.message)
        } finally {
            super.onBackPressed();
        }
    }
}
}