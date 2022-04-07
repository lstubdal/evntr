package com.codex.evntr.database

import androidx.room.*
import com.codex.evntr.API.fetchedEvent

@Dao
interface EventDAO {
    @Query("SELECT * FROM Event LIMIT 10")
    fun getEvents(): List<Event>

    @Query("SELECT * FROM event WHERE id = :eventId LIMIT 1")
    fun getEvent(eventId: String): Event

    @Query("SELECT * FROM event WHERE participate = 1")
    fun getParticipateEvent() : List<Event>

    @Insert
    fun addEvent(event: Event)

    @Update
    fun updateEvents(event: Event)

    @Query("DELETE FROM Event")
    fun deleteAllEvents()

    @Delete
    fun deleteEvent(event: Event)
}