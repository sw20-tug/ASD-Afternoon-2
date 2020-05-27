package com.example.asd.hotels

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.asd.hotels.dummy.HotelData
import kotlinx.android.synthetic.main.hotel_layout.view.*

class SortingViewAdapter(
    private val hotel_details: MutableList<HotelData>,
    private var sortByValue: SortEnum = SortEnum.PRICE
) :
    RecyclerView.Adapter<SortingViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(
            R.layout.hotel_layout,
            parent, false
        )
        return ViewHolder(layoutView)
    }

    override fun getItemCount() = hotel_details.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hotelSortedList = sortData(hotel_details)
        val hotelDetail = hotelSortedList[position]
        holder.view.txt_location.text = hotelDetail.hotel_name
        holder.view.txt_price.text = "€ ".plus(hotelDetail.price.toString())
        holder.view.img_hotel.setImageDrawable(
            holder.view.context.getDrawable(hotelDetail.image)
        )
        holder.view.ratingRatingBar.rating = hotelDetail.hotel_rating
        holder.view.starsRatingBar.rating = hotelDetail.hotel_stars.toFloat()
        holder.view.txt_description.text = hotelDetail.hotel_description;
        holder.view.setOnClickListener { view ->
            Log.d("OverViewAdapter", "clicked!")
            // Callback to the MainActivity
            //overviewClicked.invoke()
            val detail_intent = Intent(view.context, HotelDetailActivity::class.java)
            detail_intent.putExtra("hotelData", hotelDetail)
            view.context.startActivity(detail_intent)
        }
    }

    fun sortData(hotelValues: MutableList<HotelData>): List<HotelData> {
        return when (sortByValue) {
            SortEnum.PRICE -> hotelValues.sortedBy { it.price }
            SortEnum.RATING -> hotelValues.sortedByDescending { it.hotel_rating }
            SortEnum.STARS -> hotelValues.sortedByDescending { it.hotel_stars }
            SortEnum.UNSORTED -> hotelValues
        }
    }

    fun getSortByValue(): SortEnum {
        return sortByValue;
    }

    fun setSortByValue(sortEnum: SortEnum) {
        sortByValue = sortEnum
    }


    enum class SortEnum {
        PRICE, RATING, STARS, UNSORTED
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}