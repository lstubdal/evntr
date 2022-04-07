package com.codex.evntr.database

import androidx.room.*
import com.codex.evntr.API.Event

@Dao
interface EventDAO {
    @Query("SELECT * FROM eachEvent LIMIT 10")
    fun getEvents(): List<Event>

    @Query("SELECT * FROM eachEvent")
    fun dbSize(): List<Event>

    @Query("SELECT * FROM eachEvent WHERE _id = :eventId LIMIT 1")
    fun getEvent(eventId: String): Event

    @Insert
    fun addEvent(event: Event)

    @Update
    fun updateEvents(event: Event)

    @Query("DELETE FROM eachEvent")
    fun deleteAllEvents()

    @Delete
    fun deleteEvent(event: Event)
}