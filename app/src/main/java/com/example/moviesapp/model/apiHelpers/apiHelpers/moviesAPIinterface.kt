package com.example.moviesapp.model.apiHelpers.apiHelpers

import com.example.moviesapp.model.apiHelpers.dataModels.moviesDataModel.MovieSearchByNameResponse
import com.example.moviesapp.model.apiHelpers.dataModels.moviesDataModel.MoviesResponseByIDDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPIInterface {
    @GET("/")
    suspend fun getMovieByName(
        @Query("apikey") apikey: String = "5081829b",
        @Query("s") s: String = "",
        @Query("page") page: String = "1",
    ) : Response<MovieSearchByNameResponse>

    @GET("/")
    suspend fun getMovieByID(
        @Query("apikey") apikey: String = "5081829b",
        @Query("i") i: String = "",
    ) : Response<MoviesResponseByIDDataModel>
}