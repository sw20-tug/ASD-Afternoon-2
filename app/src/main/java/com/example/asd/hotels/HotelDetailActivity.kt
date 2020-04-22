package com.example.asd.hotels

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_hotel_detail.*

/**
 * An activity representing a single Hotel detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a todo change to ListActivity [*].
 */

class HotelDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_detail)
        setSupportActionBar(detail_toolbar)
        val hotelId=intent.getIntExtra("hotelId", -1) // todo get real id
        /*fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val hotel_detail_fragment = HotelDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(
                        HotelDetailFragment.ARG_ITEM_ID,
                        intent.getStringExtra(HotelDetailFragment.ARG_ITEM_ID)
                    )
                }
            }
            val hotel_picture_fragment = HotelPictureFragment().apply {
                arguments = Bundle().apply {
                    putString(
                        HotelDetailFragment.ARG_ITEM_ID,
                        intent.getStringExtra(HotelDetailFragment.ARG_ITEM_ID)
                    )
                }
            }
            val comment_fragment = CommentListFragment().apply {
                arguments = Bundle().apply {
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
        // todo set to real name
        supportActionBar?.title = "Test name";
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        if (ratingBar != null) {
            // todo set to rating saved in db
            ratingBar.rating = 4.0F;
        }
    }

    fun addRating() {
        val id = intent.getIntExtra("hotelId", -1)
        val intent = Intent(this, MainActivity::class.java) // todo change to RatingActivity
        intent.putExtra("hotelId", id)
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
