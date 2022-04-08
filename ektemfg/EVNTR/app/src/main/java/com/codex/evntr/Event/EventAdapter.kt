package com.codex.evntr.Event

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.codex.evntr.API.Event
import com.codex.evntr.EmailDialog
import com.codex.evntr.R
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.OffsetDateTime

class EventAdapter(
    private val dataset: List<Event>
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    inner class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.event_title)
        //var category : TextView = view.findViewById(R.id.event_category)
        var time: TextView = view.findViewById(R.id.event_time)
        var desc: TextView = view.findViewById(R.id.event_desc)
        var location: TextView = view.findViewById(R.id.event_location)
        var speaker: TextView = view.findViewById(R.id.event_speaker)
        //var price: TextView = view.findViewById(R.id.event_price)
        var image: ImageView = view.findViewById(R.id.event_image)
        var fav: TextView = view.findViewById(R.id.event_card_favorite)

        init {
            view.setOnClickListener {
                val thisEventID = dataset[bindingAdapterPosition]._id
                it.findNavController().navigate(EventFragmentDirections.actionEventFragmentToEventEach(thisEventID))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {

        val cellView = LayoutInflater.from(parent.context).inflate(R.layout.each_event, parent, false)

        val params: ViewGroup.LayoutParams = cellView.layoutParams
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        cellView.layoutParams = params

        return EventViewHolder(cellView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = dataset[position]

        holder.title.text = event.title.toString()

        holder.time.text = getNorwegianDate(event.time)
        holder.desc.text = event.description


       if (event.location.digitalEvent == true) {
         holder.location.text = "Online"
           holder.location.textSize = 25F

       } else {
           var address = event.location.address?.streetAddress.toString()
           var city = event.location.address?.city.toString()
           holder.location.text = "$address, $city"

        }
        var speakers = ""
        for (speaker in event.speaker) {
            speakers += "${speaker.name} "
        }
        holder.speaker.text = speakers
        Picasso.with(holder.image.context).load(event.eventImage.asset.url).into(holder.image)


    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getNorwegianDate(date: String): String {
        val date: LocalDateTime? = OffsetDateTime.parse(date).toLocalDateTime()
        return "${date?.dayOfMonth} ${date?.month} ${date?.year}"
    }

}
