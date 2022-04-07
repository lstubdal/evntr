package com.codex.evntr.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Event")
data class Event(
    @PrimaryKey val id: String,
    val category: String,
    val description: String,
    val digitalEvent: Boolean,
    val eventImage: String,
    val host: String,
    var location: String,
    val price: String,
    val speaker: String,
    val time: String,
    val title: String,
    val participate : Boolean,
)