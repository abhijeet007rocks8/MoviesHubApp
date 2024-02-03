package com.example.moviesapp.model.dummyData

import com.example.moviesapp.model.apiHelpers.dataModels.moviesDataModel.MoviesResponseByNameDataModel

class dummyDataByName {
    val movie1 = MoviesResponseByNameDataModel(
        Title = "Movie 1",
        Year = "1996",
        Poster = "https://m.media-amazon.com/images/M/MV5BMTc3NjI2MjU0Nl5BMl5BanBnXkFtZTgwNDk3ODYxMTE@._V1_SX300.jpg",
        Type = "movie"
    )

    val movie2 = MoviesResponseByNameDataModel(
        Title = "Movie 2",
        Year = "1996",
        Poster = "https://m.media-amazon.com/images/M/MV5BMTc3NjI2MjU0Nl5BMl5BanBnXkFtZTgwNDk3ODYxMTE@._V1_SX300.jpg",
        Type = "movie"
    )

    val movie3 = MoviesResponseByNameDataModel(
        Title = "Movie 3",
        Year = "1996",
        Poster = "https://m.media-amazon.com/images/M/MV5BMTc3NjI2MjU0Nl5BMl5BanBnXkFtZTgwNDk3ODYxMTE@._V1_SX300.jpg",
        Type = "movie"
    )

    val movie4 = MoviesResponseByNameDataModel(
        Title = "Movie 4",
        Year = "1996",
        Poster = "https://m.media-amazon.com/images/M/MV5BMTc3NjI2MjU0Nl5BMl5BanBnXkFtZTgwNDk3ODYxMTE@._V1_SX300.jpg",
        Type = "movie"
    )

    val movie5 = MoviesResponseByNameDataModel(
        Title = "Movie 5",
        Year = "1996",
        Poster = "https://m.media-amazon.com/images/M/MV5BMTc3NjI2MjU0Nl5BMl5BanBnXkFtZTgwNDk3ODYxMTE@._V1_SX300.jpg",
        Type = "movie"
    )

    val movie6 = MoviesResponseByNameDataModel(
        Title = "Movie 6",
        Year = "1996",
        Poster = "https://m.media-amazon.com/images/M/MV5BMTc3NjI2MjU0Nl5BMl5BanBnXkFtZTgwNDk3ODYxMTE@._V1_SX300.jpg",
        Type = "movie"
    )

    val movie7 = MoviesResponseByNameDataModel(
        Title = "Movie 7",
        Year = "1996",
        Poster = "https://m.media-amazon.com/images/M/MV5BMTc3NjI2MjU0Nl5BMl5BanBnXkFtZTgwNDk3ODYxMTE@._V1_SX300.jpg",
        Type = "movie"
    )

    val movie8 = MoviesResponseByNameDataModel(
        Title = "Movie 8",
        Year = "1996",
        Poster = "https://m.media-amazon.com/images/M/MV5BMTc3NjI2MjU0Nl5BMl5BanBnXkFtZTgwNDk3ODYxMTE@._V1_SX300.jpg",
        Type = "movie"
    )


    val movie9 = MoviesResponseByNameDataModel(
        Title = "Movie 9",
        Year = "1996",
        Poster = "https://m.media-amazon.com/images/M/MV5BMTc3NjI2MjU0Nl5BMl5BanBnXkFtZTgwNDk3ODYxMTE@._V1_SX300.jpg",
        Type = "movie"
    )

    fun getDummyData(): List<MoviesResponseByNameDataModel> {
        return listOf<MoviesResponseByNameDataModel>(
            movie1,
            movie2,
            movie3,
            movie4,
            movie5,
            movie6,
            movie7,
            movie8,
            movie9,
        )
    }
}