package com.example.travniktourist.activities

import retrofit2.Response
import retrofit2.http.GET

interface ActivitiesApi {
    @GET("fun_data.json")
    suspend fun getActivities(): Response<List<Activities>>
}