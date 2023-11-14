package com.example.travniktourist.data

import com.squareup.moshi.Json

data class Product(
        @Json(name = "foodName")
        val name: String,
        val imageFile: String,
        val description: String,
        val duration: String,
        val address: String,
        val type: String,
        val location: String
)

