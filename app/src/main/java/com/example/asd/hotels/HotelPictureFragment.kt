package com.example.asd.hotels

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asd.hotels.dummy.HotelData
import kotlinx.android.synthetic.main.fragment_hotel_picture.*

/**
 * A fragment representing a single Hotel detail screen.
 * This fragment is either contained in a  //todo ListActivity [*]
 * in two-pane mode (on tablets) or a [HotelDetailActivity]
 * on handsets.
 */
class HotelPictureFragment : Fragment() {
    private lateinit var hotelData: HotelData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(HotelPictureFragment.ARG_ITEM)) {
                // Load the  content specified by the fragment
                val item = it.getParcelable<HotelData>(HotelPictureFragment.ARG_ITEM);
                if (item != null) {
                    hotelData = item
                }
            }
        }



    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_hotel_picture, container, false)
        // get pictures
        var images =
            listOf(R.drawable.untitled,  R.drawable.untitled,  R.drawable.untitled)
        // Setting up the adapter
        val photoview = rootView.findViewById(R.id.PhotoView) as RecyclerView
        photoview.layoutManager = LinearLayoutManager(activity)
        photoview.adapter =  PhotoViewAdapter(images) {
                // Call the detail view.
                startActivity(
                    Intent(
                        this@HotelPictureFragment.context,
                        HotelDetailActivity::class.java
                    )
                )
        }
        return rootView
    }


    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "hotel_id"
        const val ARG_ITEM = "hotelData"
    }
}
