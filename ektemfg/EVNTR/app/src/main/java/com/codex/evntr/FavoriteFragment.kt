package com.codex.evntr

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.Volley
import com.codex.evntr.Event.EventAdapter
import com.codex.evntr.Event.EventViewModel
import com.codex.evntr.database.AppDatabase

class FavoriteFragment : Fragment() {
    private lateinit var eventRecyclerView: RecyclerView
    private lateinit var eventLayoutManager: LinearLayoutManager
    private lateinit var eventAdapter: EventAdapter
    private val viewModel: EventViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.eventDao = AppDatabase.getInstance(requireContext()).eventDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventRecyclerView = view.findViewById(R.id.fav_recyclerView)
        eventLayoutManager = LinearLayoutManager(activity)
        eventRecyclerView.layoutManager = eventLayoutManager




        viewModel.getAllEvents(Volley.newRequestQueue(context)) { events ->
            if (events != null) {
                eventAdapter = EventAdapter(events)
                eventRecyclerView.adapter = eventAdapter
            }

        }
        var back : Button = view.findViewById(R.id.going_back)

        back.setOnClickListener {
            findNavController().popBackStack()
        }
    }


}

