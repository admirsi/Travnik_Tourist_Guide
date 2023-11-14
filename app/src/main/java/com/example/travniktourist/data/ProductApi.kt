package com.example.travniktourist.data

import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {
    @GET("food_data.json")
    suspend fun getProducts(): Response<List<Product>>
}