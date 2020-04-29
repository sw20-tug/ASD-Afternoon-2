package com.example.asd.hotels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_hotel_detail.*
import kotlinx.android.synthetic.main.hotel_detail.view.*
import kotlinx.android.synthetic.main.hotel_layout.view.*

/**
 * A fragment representing a single Hotel detail screen.
 * This fragment is either contained in a  //todo ListActivity [*]
 * in two-pane mode (on tablets) or a [HotelDetailActivity]
 * on handsets.
 */
class HotelDetailFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
    private var item = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // Load the  content specified by the fragment
                //item = DummyContent.ITEM_MAP[it.getString(ARG_ITEM_ID)]
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.hotel_detail, container, false)
        rootView.hotel_detail_text.text = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua."//it.details
        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "hotel_id"
    }
}
