package com.codex.evntr.Event

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codex.evntr.API.Event
import com.codex.evntr.R
import com.squareup.picasso.Picasso

class EventAdapter(
    private val dataset: List<Event>
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    inner class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.event_desc)
        var category : TextView = view.findViewById(R.id.event_category)
        var time: TextView = view.findViewById(R.id.event_time)
        var location: TextView = view.findViewById(R.id.event_location)
        var speaker: TextView = view.findViewById(R.id.event_speaker)
        var price: TextView = view.findViewById(R.id.event_price)
        var image: ImageView = view.findViewById(R.id.event_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {

        val cellView = LayoutInflater.from(parent.context).inflate(R.layout.each_event, parent, false)

        val params: ViewGroup.LayoutParams = cellView.layoutParams
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        cellView.layoutParams = params

        return EventViewHolder(cellView)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = dataset[position]

        holder.title.text = event.title
        holder.category.text = event.category.type
        holder.time.text = event.time
        if (event.digitalEvent == true) {
            holder.price.text = "GRATIS"
            holder.location.text = "Online"
        } else {
            holder.price.text = event.price.amount?.toString()
            holder.location.text = event.location?.address
        }
        var speakers = ""
        for (speaker in event.speaker) {
            speakers += speaker.name
        }
        holder.speaker.text = speakers
        Picasso.with(holder.image.context).load(event.eventImage.asset.url).into(holder.image)

    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}
