package com.codex.evntr.API

data class ApiFullResponse(
    val ms: Int?,
    val query: String?,
    val result: ApiEvent
)