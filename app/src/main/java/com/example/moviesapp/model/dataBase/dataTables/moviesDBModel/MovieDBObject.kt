package com.example.moviesapp.model.dataBase.dataTables.moviesDBModel

class MovieDBObject(
    imdbID: String,
    title: String,
    year: String,
    plot: String,
    poster: String,
    imdbRating: String,
    isFavourite: Boolean,
    rated: String,
    released: String,
    runtime: String,
    genre: String,
    director: String,
    writer: String,
    actors: String,
    language: String,
    country: String,
    awards: String,
    metascore: String,
    imdbVotes: String,
    type: String,
    DVD: String,
    boxOffice: String,
    production: String,
    website: String
) {
    var imdbID: String = imdbID   // Primary Key
    var title: String = title
    var year: String = year
    var plot: String = plot
    var poster: String = poster
    var imdbRating: String= imdbRating
    var isFavourite: Boolean= isFavourite


    var rated: String= rated
    var released: String= released
    var runtime: String= runtime
    var genre: String= genre
    var director: String=director
    var writer: String=writer
    var actors: String=actors
    var language: String=language
    var country: String=country
    var awards: String=awards
    var metascore: String=metascore
    var imdbVotes: String=imdbVotes
    var type: String=type
    var DVD: String=DVD
    var boxOffice: String=boxOffice
    var production: String=production
    var website: String=website
}