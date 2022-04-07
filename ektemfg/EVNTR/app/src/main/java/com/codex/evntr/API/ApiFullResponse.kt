package com.codex.evntr.API

import com.codex.evntr.database.Event

data class ApiFullResponse(
    val ms: Int?,
    val query: String?,
    val result: List<fetchedEvent>?
)