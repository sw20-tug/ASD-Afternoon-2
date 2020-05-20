package com.example.asd.hotels

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity
import com.example.asd.hotels.dummy.HotelData
import kotlinx.android.synthetic.main.activity_hotel_detail.*

/**
 * An activity representing a single Hotel detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a todo change to ListActivity [*].
 */

class HotelDetailActivity(): AppCompatActivity() {
    private lateinit var hotelData: HotelData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_detail)
        setSupportActionBar(detail_toolbar)

        hotelData = intent.getParcelableExtra("hotelData")

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val hotel_detail_fragment = HotelDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(HotelDetailFragment.ARG_ITEM,
                        hotelData)
                    putInt(
                        HotelDetailFragment.ARG_ITEM_ID,
                        hotelData.hotel_id
                    )
                }
            }
            val hotel_picture_fragment = HotelPictureFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(HotelPictureFragment.ARG_ITEM,
                        hotelData)
                    putInt(
                        HotelPictureFragment.ARG_ITEM_ID,
                        hotelData.hotel_id
                    )
                }
            }
            val comment_fragment = CommentListFragment().apply {
                arguments = Bundle().apply {
                    putInt(
                        HotelDetailFragment.ARG_ITEM_ID,
                        hotelData.hotel_id
                    )
                }
            }
            supportFragmentManager.beginTransaction()
                .add(R.id.hotel_detail_container, hotel_detail_fragment)
                .add(R.id.hotel_picture_container, hotel_picture_fragment)
                .add(R.id.comment_list_container, comment_fragment)
                .commit()
        }
        initializeHotelInfos()
        // add button and eventlistener
        val ratingButton = findViewById<Button>(R.id.rating_button)
        ratingButton.setOnClickListener{ view -> addRating() }
    }
    fun initializeHotelInfos() {
        supportActionBar?.title = hotelData.hotel_name
        val hotelStars: RatingBar = findViewById<RatingBar>(R.id.hotelStars)
        if (hotelStars != null) {
            hotelStars.rating = (hotelData.hotel_stars).toFloat();
        }
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        if (ratingBar != null) {
            ratingBar.rating = (hotelData.hotel_rating).toFloat();
        }
    }

    fun addRating() {
        //val id = intent.getIntExtra("hotel_id", -1)
        val intent = Intent(this, HotelRatingActivity::class.java)
        intent.putExtra("hotel_id", hotelData.hotel_id)
        startActivity(intent)
    }


    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                // todo change to ListActivity
                navigateUpTo(Intent(this, MainActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}

