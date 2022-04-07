package com.codex.evntr.Event

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codex.evntr.R
import com.android.volley.toolbox.Volley


class EventFragment : Fragment() {
    private lateinit var myRecyclerView: RecyclerView
    private lateinit var myLayoutManager: LinearLayoutManager
    private lateinit var myAdapter: EventAdapter
    private val viewModel: EventsViewHolder by viewModels()
    lateinit var eventsButton: ImageButton
    lateinit var goingButton: ImageButton
    lateinit var profileButton: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        myRecyclerView = view.findViewById(R.id.Event_RecyclerView)
        myLayoutManager = LinearLayoutManager(activity)
        myRecyclerView.layoutManager = myLayoutManager
        goingButton = view.findViewById(R.id.fav_button) as ImageButton
        profileButton = view.findViewById(R.id.profile_button) as ImageButton
        eventsButton = view.findViewById(R.id.events_button) as ImageButton

        goingButton.setOnClickListener{
            findNavController().navigate(R.id.action_eventFragment_to_favoriteFragment)
            Toast.makeText(activity, "You clicked on ImageView.", Toast.LENGTH_SHORT).show()
        }
        profileButton.setOnClickListener{
            findNavController().navigate(R.id.action_eventFragment_to_profileFragment)
        }
        eventsButton.setOnClickListener{
            findNavController().navigate(R.id.action_eventFragment_self)
        }



        val eventsList = viewModel.getAllEvents(Volley.newRequestQueue(context)) { events ->
            if (events != null) {
                myAdapter = EventAdapter(events)
                myRecyclerView.adapter = myAdapter
            }

        }
        }


    }

