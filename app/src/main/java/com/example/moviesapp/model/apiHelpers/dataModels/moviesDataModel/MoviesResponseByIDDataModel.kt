package com.example.moviesapp.model.apiHelpers.dataModels.moviesDataModel

import com.google.gson.annotations.SerializedName

data class MoviesResponseByIDDataModel(
    @SerializedName("Title") val Title: String?=null,
    @SerializedName("Year") val Year: String?=null,
    @SerializedName("Rated") val Rated: String?=null,
    @SerializedName("Released") val Released: String?=null,
    @SerializedName("Runtime") val Runtime: String?=null,
    @SerializedName("Genre") val Genre: String?=null,
    @SerializedName("Director") val Director: String?=null,
    @SerializedName("Writer") val Writer: String?=null,
    @SerializedName("Actors") val Actors: String?=null,
    @SerializedName("Plot") val Plot: String?=null,
    @SerializedName("Language") val Language: String?=null,
    @SerializedName("Country") val Country: String?=null,
    @SerializedName("Awards") val Awards: String?=null,
    @SerializedName("Poster") val Poster: String?=null,
    @SerializedName("Ratings") val Ratings: List<RatingsDataModel>?=null,
    @SerializedName("Metascore") val Metascore: String?=null,
    @SerializedName("imdbRating") val imdbRating: String?=null,
    @SerializedName("imdbVotes") val imdbVotes: String?=null,
    @SerializedName("imdbID") val imdbID: String?=null,
    @SerializedName("Type") val Type: String?=null,
    @SerializedName("DVD") val DVD: String?=null,
    @SerializedName("BoxOffice") val BoxOffice: String?=null,
    @SerializedName("Production") val Production: String?=null,
    @SerializedName("Website") val Website: String?=null,
    @SerializedName("Response") val Response: String,
    @SerializedName("Error") val Error: String?=null,
)
