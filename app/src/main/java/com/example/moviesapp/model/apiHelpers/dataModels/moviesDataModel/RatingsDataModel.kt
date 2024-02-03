package com.example.moviesapp.model.apiHelpers.dataModels.moviesDataModel

import com.google.gson.annotations.SerializedName

data class RatingsDataModel(
    @SerializedName("Source") val Source: String?=null,
    @SerializedName("Value") val Value: String?=null,
)
