package com.codex.evntr.Event

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codex.evntr.R
import com.android.volley.toolbox.Volley
import com.codex.evntr.EmailDialog
import com.codex.evntr.database.AppDatabase


class EventFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventRecyclerView = view.findViewById(R.id.Event_RecyclerView)
        eventLayoutManager = LinearLayoutManager(activity)
        eventRecyclerView.layoutManager = eventLayoutManager




        viewModel.getAllEvents(Volley.newRequestQueue(context)) { events ->
            if (events != null) {
                eventAdapter = EventAdapter(events)
                eventRecyclerView.adapter = eventAdapter
            }

        }
        }


    }

