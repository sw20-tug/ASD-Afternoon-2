package com.example.asd.hotels.dummy

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HotelData (
    var hotel_id: Int,
    var category: Int,
    var location: String,
    var image: Int,
    var hotel_name: String,
    var hotel_capacity: Int,
    var price: Int,
    var hotel_description: String,
    var hotel_rating: Float,
    var hotel_stars: Int
) : Parcelable

