package com.codex.evntr.database
import androidx.room.TypeConverter
import com.beust.klaxon.Klaxon
import com.codex.evntr.API.*

class TypeConverters {

    @TypeConverter
    fun assetToJSON(asset: EventImageObject): String = Klaxon().toJsonString(asset)

    @TypeConverter
    fun hostArrayListToJSON(host: ArrayList<Host>): String = Klaxon().toJsonString(host)

    @TypeConverter
    fun hostToJSON(host: Host): String = Klaxon().toJsonString(host)

    @TypeConverter
    fun locationToJSON(location: Location): String = Klaxon().toJsonString(location)

    @TypeConverter
    fun categoryToJSON(category: Category): String = Klaxon().toJsonString(category)

    @TypeConverter
    fun priceToJSON(price: Price): String = Klaxon().toJsonString(price)

    @TypeConverter
    fun speakerArrayListToJSON(speaker: ArrayList<Speaker>): String = Klaxon().toJsonString(speaker)
    @TypeConverter
    fun speakerToJSON(speaker: Speaker): String = Klaxon().toJsonString(speaker)

    @TypeConverter
    fun eventToJSON(event: Event): String = Klaxon().toJsonString(event)

    @TypeConverter
    fun JSONtoasset(json: String): EventImageObject? = Klaxon().parse<EventImageObject>(json)

    @TypeConverter
    fun JSONArrayListtohost(json: String): ArrayList<Host>? = ArrayList(Klaxon().parseArray<Host>(json) ?: listOf())

    @TypeConverter
    fun JSONtohost(json: String): Host? = Klaxon().parse<Host>(json)

    @TypeConverter
    fun JSONtolocation(json: String): Location? = Klaxon().parse<Location>(json)

    @TypeConverter
    fun JSONtoCategory(json: String): Category? = Klaxon().parse<Category>(json)

    @TypeConverter
    fun JSONtoPrice(json: String): Price? = Klaxon().parse<Price>(json)

    @TypeConverter
    fun JSONtoSpeakerArrayList(json: String): ArrayList<Speaker>? = ArrayList(Klaxon().parseArray<Speaker>(json) ?: listOf())

    @TypeConverter
    fun JSONtoSpeaker(json: String): Speaker? = Klaxon().parse<Speaker>(json)

    @TypeConverter
    fun JSONtoEvent(json: String): Event? = Klaxon().parse<Event>(json)

}