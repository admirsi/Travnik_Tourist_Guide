package com.example.travniktourist.activities

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_ENDPOINT_URL = "https://admirsi.github.io/TravnikTouristGuideWeb/"

class ActivitiesRepository {
    private val retrofit: Retrofit by lazy {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        Retrofit.Builder()
            .baseUrl(BASE_ENDPOINT_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    private val activitiesApi: ActivitiesApi by lazy {
        retrofit.create(ActivitiesApi::class.java)
    }

    suspend fun getActivities(): List<Activities> {
        val response = activitiesApi.getActivities()
        return if (response.isSuccessful)
            response.body() ?: emptyList()
        else
            emptyList()
    }
}
