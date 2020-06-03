package com.example.asd.hotels

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.fragment_comment_list.*
import kotlinx.android.synthetic.main.fragment_comment_list.view.*

private const val ARG_ITEM_ID = "hotel_id"
/**
 * A simple [Fragment] subclass.
 * Use the [CommentListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommentListFragment : Fragment() {

    private var hotel_id: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            hotel_id = it.getInt(ARG_ITEM_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_comment_list, container, false)


        // todo use real comments
        val comments = listOf(
            "It was nice!", "Great view!", "Too many spiders.",
            "Nice guests!"
        )
        val context: Context = this.context ?: return rootView //
        val listViewAdapter: ArrayAdapter<String>
        listViewAdapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, comments)
        rootView.comment_list.adapter = listViewAdapter
        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param hotel_id Hotel id
         * @return A new instance of fragment CommentListFragment.
         */

        @JvmStatic
        fun newInstance(hotel_id: Int) =
            CommentListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ITEM_ID, hotel_id)
                }
            }
    }
}
