package com.example.travniktourist.events

import com.squareup.moshi.Json

data class Events(
    @Json(name = "eventName")
    val name: String,
    val imageFile: String,
    val description: String,
    val time: String,
    val price: String,
    val location: String
)

