package com.codex.evntr.Event

import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.beust.klaxon.Klaxon
    import com.codex.evntr.API.ApiFullResponse
import com.codex.evntr.API.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.codex.evntr.database.EventDAO


class EventViewModel : ViewModel() {
    lateinit var eventDao: EventDAO


    fun getAllEvents(queue: RequestQueue, callback: (List<Event>?) ->  Unit) {
        val url = "https://3ii107lu.api.sanity.io/v2021-10-21/data/query/production?query=%7B%0A%20%20%22event%22%3A%20%20*%5B_type%20%3D%3D%20%27event%27%5D%20%7B%0A%0A%20%20%20...%2C%0A%20%20%0A%20%20location%20%7B%0A%20%20%20%20digitalEvent%2C%0A%0A%20%20%20%20address-%3E%20%7B%0A%20%20%20%20%20streetAddress%2C%0A%20%20%20%20%20city%0A%20%20%20%7D%0A%7D%2C%0A%20%20%0A%20%20%20category-%3E%20%7B%0A%20%20%20%20%20type%0A%20%20%20%7D%2C%0A%0A%20%20%20eventImage%20%7B%0A%20%20%20%20%20asset-%3E%20%7B%0A%20%20%20%20%20url%0A%20%20%20%20%7D%0A%20%20%20%7D%2C%0A%0A%20%20%20host%5B%5D-%3E%20%7B%0A%20%20%20%20%20name%0A%20%20%20%7D%2C%0A%0A%20%20%20speaker%5B%5D-%3E%20%7B%0A%20%20%20%20%20name%0A%20%20%20%7D%2C%0A%20%20%20%0A%0A%20%20%20price%0A%20%20%7D%2C%0A%0A%0A%20%20%22locations%22%3A%20*%5B_type%20%3D%3D%20%27location%27%5D%20%7C%20order(city%20asc)%20%7B%0A%20%20%20%20city%0A%20%20%7D%2C%0A%0A%0A%7D"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                val fetched = Klaxon().parse<ApiFullResponse?>(response)
                if (fetched != null) {
                    callback(fetched.result.event)
                    if (eventDao.dbSize().size > 0) {
                        saveToDB(fetched.result.event)
                    } else {
                        updateDB(fetched.result.event)
                    }
                }
            },
            { error ->
                callback(null)
            }
        )

        queue.add(stringRequest)
    }

    private fun updateDB(result: List<Event>) {
        CoroutineScope(Dispatchers.IO).launch {
            for (event in result) {
                eventDao.updateEvents(event)
            }

        }.start()
    }

    private fun saveToDB(result: List<Event>) {
        CoroutineScope(Dispatchers.IO).launch {
                for (event in result) {
                    eventDao.addEvent(event)
                }

        }.start()
    }
}