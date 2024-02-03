package com.example.moviesapp.model.apiHelpers.apiHelpers

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitObject {
    val baseUrl = "https://www.omdbapi.com/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
}