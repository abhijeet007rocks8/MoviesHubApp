package com.example.moviesapp.model.apiHelpers.dataModels.moviesDataModel

import com.google.gson.annotations.SerializedName

data class MoviesResponseByNameDataModel(
    @SerializedName("Title") val Title: String?=null,
    @SerializedName("Year") val Year: String?=null,
    @SerializedName("Poster") val Poster: String?=null,
    @SerializedName("imdbID") val imdbID: String?=null,
    @SerializedName("Type") val Type: String?=null,
)
