package com.example.asd.hotels.dummy

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HotelData (
    val hotel_id: Int,
    val category: Int,
    val location: String,
    val image: Int,
    val hotel_name: String,
    val hotel_capacity: Int,
    val price: Int,
    val hotel_description: String,
    val hotel_rating: Int,
    val hotel_stars: Int
) : Parcelable

