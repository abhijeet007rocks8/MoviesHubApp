package com.example.moviesapp.model.apiHelpers.dataModels.moviesDataModel

import com.google.gson.annotations.SerializedName

data class MovieSearchByNameResponse(
    @SerializedName("Search") val Search: ArrayList<MoviesResponseByNameDataModel>?=null,
    @SerializedName("totalResults") val totalResults: String?=null,
    @SerializedName("Response") val Response: String="True",
    @SerializedName("Error") val Error: String?=null,
)
