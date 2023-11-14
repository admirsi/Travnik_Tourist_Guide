package com.example.travniktourist.events

import retrofit2.Response
import retrofit2.http.GET

interface EventsApi {
    @GET("events_new_data.json")
    suspend fun getEvents(): Response<List<Events>>
}