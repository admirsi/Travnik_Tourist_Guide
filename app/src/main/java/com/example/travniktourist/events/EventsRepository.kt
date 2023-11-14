package com.example.travniktourist.events

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_ENDPOINT_URL = "https://admirsi.github.io/TravnikTouristGuideWeb/"

class EventsRepository {
    private val retrofit: Retrofit by lazy {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        Retrofit.Builder()
            .baseUrl(BASE_ENDPOINT_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    private val eventsApi: EventsApi by lazy {
        retrofit.create(EventsApi::class.java)
    }

    suspend fun getEvents(): List<Events> {
        val response = eventsApi.getEvents()
        return if (response.isSuccessful)
            response.body() ?: emptyList()
        else
            emptyList()
    }
}
