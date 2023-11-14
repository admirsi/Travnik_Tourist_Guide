package com.example.travniktourist.activities

import com.squareup.moshi.Json

data class Activities(
    @Json(name = "funName")
    val name: String,
    val imageFile: String,
    val description: String,
    val duration: String,
    val price: String,
    val address: String,
    val type: String,
    val location: String
)

