package com.codex.evntr.Event

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.volley.toolbox.Volley
import com.codex.evntr.EmailDialog
import com.codex.evntr.R
import com.codex.evntr.database.AppDatabase
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.OffsetDateTime
import android.content.Intent
import com.codex.evntr.FavoriteFragment


class EventEach : Fragment() {
    private val viewModel: EventViewModel by viewModels()
    private val naviArguments: EventEachArgs by navArgs()
    private lateinit var each_picture: ImageView
    private lateinit var each_title: TextView
    private lateinit var each_time: TextView
    private lateinit var each_location: TextView
    private lateinit var each_price: TextView
    private lateinit var each_going: TextView
    private lateinit var each_category: TextView
    private lateinit var each_speaker: TextView





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.eventDao = AppDatabase.getInstance(requireContext()).eventDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_each, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val wantedEventID = naviArguments.thisEventID
        var rightIndex = 0
         each_picture = view.findViewById(R.id.each_picture)
         each_title = view.findViewById(R.id.each_title)
         each_time= view.findViewById(R.id.each_time)
         each_location= view.findViewById(R.id.each_location)
         each_price= view.findViewById(R.id.each_price)
         each_going = view.findViewById(R.id.each_going)
         each_category= view.findViewById(R.id.each_category)
         each_speaker= view.findViewById(R.id.each_speaker)





        viewModel.getEventByID(Volley.newRequestQueue(context), wantedEventID) { wantedEvent ->
            if (wantedEvent != null) {
                for ((index, event) in wantedEvent.withIndex()) {
                    if (event._id == wantedEventID) {
                        rightIndex = index
                        break
                    }
                    else {
                        Log.d("searchIndex", "Wrong index $index")
                    }
                }
                each_title.text = wantedEvent[rightIndex].title
                each_going.text = "JOIN ♥ EVENT"
                each_going.textSize = 30F
                var back : Button = view.findViewById(R.id.eachEvent_back)

                back.setOnClickListener {
                    findNavController().popBackStack()
                }
                each_going.setOnClickListener{
                    Toast.makeText(context, "Skriv inn epost, vi sender invitasjon.", Toast.LENGTH_SHORT).show()
                    var eventTitle = each_title.text.toString()
                    var eventUniqueNumber = wantedEvent[rightIndex]._rev
                    var items = arrayListOf<String>(eventTitle, eventUniqueNumber)
                    val emailDialog = EmailDialog.newInstance(items)
                    emailDialog.show(requireActivity().supportFragmentManager, "emailDialog")


                }
                each_category.text = wantedEvent[rightIndex].category.type
                each_time.text = getNorwegianDate(wantedEvent[rightIndex].time)


                if (wantedEvent[rightIndex].location.digitalEvent == true) {
                    each_location.text = "Online"
                    each_location.textSize = 25F
                    each_price.text = "FREE"

                } else {
                    var address = wantedEvent[rightIndex].location.address?.streetAddress.toString()
                    var city = wantedEvent[rightIndex].location.address?.city.toString()
                    each_location.text = "$address, $city"
                    each_price.text = "${wantedEvent[rightIndex].price.amount.toString()} NOK"

                }
                var speakers = ""
                for (speaker in wantedEvent[rightIndex].speaker) {
                    speakers += "${speaker.name} "
                }
                each_speaker.text = speakers
                Picasso.with(each_picture.context).load(wantedEvent[rightIndex].eventImage.asset.url).into(each_picture)



            }


        }

    }


        @RequiresApi(Build.VERSION_CODES.O)
        fun getNorwegianDate(date: String): String {
            val date: LocalDateTime? = OffsetDateTime.parse(date).toLocalDateTime()
            return "${date?.dayOfMonth} ${date?.month} ${date?.year}"
        }

    }



