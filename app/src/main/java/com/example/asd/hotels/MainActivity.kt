package com.example.asd.hotels

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.util.Log.d
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asd.hotels.dummy.HotelData
import com.example.asd.hotels.provider.DatabaseProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_main_sorted.*
import kotlinx.android.synthetic.main.hotel_layout.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    lateinit var adapter_: OverViewAdapter
    lateinit var adapter_sort: SortingViewAdapter
    lateinit var hotelValues: MutableList<HotelData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        hotelValues = mutableListOf<HotelData>()

        adapter_sort = SortingViewAdapter(hotelValues, SortingViewAdapter.SortEnum.UNSORTED)



        var nameList = resources.getStringArray(R.array.nameList)

        try {
            val connectMySql = DatabaseProvider(this)

            connectMySql.insert_location("Alpha")
            connectMySql.insert_location("Beta")
            connectMySql.insert_location("Charlie")
            connectMySql.insert_location("Delta")
            connectMySql.insert_location("Echo")
            connectMySql.insert_location("Foxtrot")
            connectMySql.insert_location("Golf")
            connectMySql.insert_location("Hotel")
            connectMySql.insert_location("India")

            for (x in 0 until 10) {
                connectMySql.insert_category("Test")
                connectMySql.insert_photo(R.drawable.untitled)
            }

            for (x in 0 until 10) {
                connectMySql.insert_hotel(
                    1,
                    x + 1,
                    "Hotel " + nameList[Random.nextInt(0, nameList.size - 1)],
                    Random.nextInt(1, 100),
                    Random.nextInt(1, 100),
                    "Gutes Hotel!",
                    (Random.nextInt(0, 9) + Random.nextFloat()),
                    Random.nextInt(1, 5),
                    x + 1
                )
            }

            for (x in 1 until 11) {
                hotelValues.add(connectMySql.get_hotels(x))
            }
        } catch (e: Exception) {
            d("MainActivity Db error", e.message)
        }

        var overViewVisible = true
        var sortedBy = SortingViewAdapter.SortEnum.UNSORTED
        sort_button_price.setOnClickListener {
            sort_button_rating.isChecked = false
            sort_button_stars.isChecked = false
            adapter_sort = SortingViewAdapter(hotelValues, SortingViewAdapter.SortEnum.PRICE)
            SortingView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = adapter_sort
            }
            overViewVisible = if (overViewVisible) {
                OverView.setVisibility(View.INVISIBLE)
                SortingView.setVisibility(View.VISIBLE)
                sortedBy = SortingViewAdapter.SortEnum.PRICE
                false
            } else {
                if (sortedBy == SortingViewAdapter.SortEnum.PRICE) {
                    OverView.setVisibility(View.VISIBLE)
                    SortingView.setVisibility(View.GONE)
                    sortedBy = SortingViewAdapter.SortEnum.UNSORTED
                    true
                } else {
                    sortedBy = SortingViewAdapter.SortEnum.PRICE
                    false
                }
            }
        }
        sort_button_rating.setOnClickListener {
            sort_button_price.isChecked = false
            sort_button_stars.isChecked = false
            adapter_sort = SortingViewAdapter(hotelValues, SortingViewAdapter.SortEnum.RATING)
            SortingView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = adapter_sort
            }
            overViewVisible = if (overViewVisible) {
                OverView.setVisibility(View.INVISIBLE)
                SortingView.setVisibility(View.VISIBLE)
                sortedBy = SortingViewAdapter.SortEnum.RATING
                false
            } else {
                if (sortedBy == SortingViewAdapter.SortEnum.RATING) {
                    OverView.setVisibility(View.VISIBLE)
                    SortingView.setVisibility(View.GONE)
                    sortedBy = SortingViewAdapter.SortEnum.UNSORTED
                    true
                } else {
                    sortedBy = SortingViewAdapter.SortEnum.RATING
                    false
                }
            }
        }

        sort_button_stars.setOnClickListener {
            sort_button_price.isChecked = false
            sort_button_rating.isChecked = false
            adapter_sort = SortingViewAdapter(hotelValues, SortingViewAdapter.SortEnum.STARS)
            SortingView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = adapter_sort
            }
            overViewVisible = if (overViewVisible) {
                OverView.setVisibility(View.INVISIBLE)
                SortingView.setVisibility(View.VISIBLE)
                sortedBy = SortingViewAdapter.SortEnum.STARS
                false
            } else {
                if (sortedBy == SortingViewAdapter.SortEnum.STARS) {
                    OverView.setVisibility(View.VISIBLE)
                    SortingView.setVisibility(View.GONE)
                    sortedBy = SortingViewAdapter.SortEnum.UNSORTED
                    true
                } else {
                    sortedBy = SortingViewAdapter.SortEnum.STARS
                    false
                }
            }
        }





        // Setting up the adapter
        adapter_ = OverViewAdapter(hotelValues) {
            d("MainActivity", "Hi from main")
            // Call the detail view.
            startActivity(
                Intent(
                    this@MainActivity,
                    HotelDetailActivity::class.java
                )
            )
        }
        OverView.apply {
            // Set up the layer
            layoutManager = LinearLayoutManager(this@MainActivity)
            // Pass the list into OverViewAdapter
            adapter = adapter_
        }

        // Add item separator to the overview.
        val itemDecor = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        OverView.addItemDecoration(itemDecor)
        SortingView.addItemDecoration(itemDecor)
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
            R.id.action_language -> {
                if (textView3.text == this.applicationContext.resources
                        .getString(R.string.sorting_text)
                ) {
                    textView3.setText(R.string.sorting_textd)
                    sort_button_stars.setText(R.string.sorting_text_starsd)
                    sort_button_stars.setTextOn(this.applicationContext.resources
                        .getString(R.string.sorting_text_starsd))
                    sort_button_stars.setTextOff(this.applicationContext.resources
                        .getString(R.string.sorting_text_starsd))
                    sort_button_rating.setText(R.string.sorting_text_ratingd)
                    sort_button_rating.setTextOn(this.applicationContext.resources
                        .getString(R.string.sorting_text_ratingd))
                    sort_button_rating.setTextOff(this.applicationContext.resources
                        .getString(R.string.sorting_text_ratingd))
                    sort_button_price.setText(R.string.sorting_text_priced)
                    sort_button_price.setTextOn(this.applicationContext.resources
                        .getString(R.string.sorting_text_priced))
                    sort_button_price.setTextOff(this.applicationContext.resources
                        .getString(R.string.sorting_text_priced))
                    textView.setText(R.string.starsStringd)
                    textView2.setText(R.string.ratingStringd)
                    adapter_.notifyDataSetChanged()
                    adapter_sort.notifyDataSetChanged()
                }
                else
                {
                    textView3.setText(R.string.sorting_text)
                    sort_button_stars.setText(R.string.sorting_text_stars)
                    sort_button_stars.setTextOn(this.applicationContext.resources
                        .getString(R.string.sorting_text_stars))
                    sort_button_stars.setTextOff(this.applicationContext.resources
                        .getString(R.string.sorting_text_stars))
                    sort_button_rating.setText(R.string.sorting_text_rating)
                    sort_button_rating.setTextOn(this.applicationContext.resources
                        .getString(R.string.sorting_text_rating))
                    sort_button_rating.setTextOff(this.applicationContext.resources
                        .getString(R.string.sorting_text_rating))
                    sort_button_price.setText(R.string.sorting_text_price)
                    sort_button_price.setTextOn(this.applicationContext.resources
                        .getString(R.string.sorting_text_price))
                    sort_button_price.setTextOff(this.applicationContext.resources
                        .getString(R.string.sorting_text_price))
                    textView.setText(R.string.starsString)
                    textView2.setText(R.string.ratingString)
                    adapter_.notifyDataSetChanged()
                    adapter_sort.notifyDataSetChanged()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
//    external fun stringFromJNI(): String
//
//    companion object {
//
//        // Used to load the 'native-lib' library on application startup.
//        init {
//            System.loadLibrary("native-lib")
//        }
//    }
}

