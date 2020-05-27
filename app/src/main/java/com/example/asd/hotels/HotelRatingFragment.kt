package com.example.asd.hotels

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updatePaddingRelative
import com.example.asd.hotels.dummy.HotelData
import kotlinx.android.synthetic.main.activity_hotel_rating.view.*
import kotlinx.android.synthetic.main.hotel_detail.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [HotelRatingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HotelRatingFragment : Fragment() {

    private lateinit var hotelData: HotelData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if(it.containsKey(ARG_ITEM)) {
                val item = it.getParcelable<HotelData>(ARG_ITEM);
                if(item != null) {
                    hotelData = item
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.activity_hotel_rating, container, false)
        rootView.hotel_name.text = hotelData.hotel_name
        //rootView.hotel_picture_container_ratingview
        return rootView
    }

    companion object {
        const val ARG_ITEM_ID = "hotel_id"
        const val ARG_ITEM = "hotelData"
    }
}
