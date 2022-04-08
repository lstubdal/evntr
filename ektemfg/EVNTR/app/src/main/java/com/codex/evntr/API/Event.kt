package com.codex.evntr.API

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "eachEvent")
data class Event (
    @PrimaryKey var _id: String,
    var _createdAt: String,
    var _rev: String,
    var _type: String,
    var _updatedAt: String,
    var category: Category,
    var description: String,
    var eventImage: EventImageObject,
    var host: ArrayList<Host>,
    var location: Location,
    var digitalEvent: Boolean? = null,
    var price: Price,
    var speaker: ArrayList<Speaker>,
    var time: String,
    var title: String
    )

    data class EventImageObject (
        var asset: AssetObject
    )

class AssetObject (
var url: String
    )

data class Category (
        var type: String
    )

    data class Host (
        var name: String
    )

    data class Location (
        var address : Address?,
        var digitalEvent: Boolean? = null
    )

class Address (
var city: String,
        var streetAddress: String
)

data class Price (
        var amount: Int? = null,
        var free: Boolean?

    )

    data class Speaker (
        var name : String
    )


