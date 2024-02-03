package com.example.moviesapp.model.dataBase.dataTables.moviesDBModel

import com.example.moviesapp.model.dataBase.AppDatabase
import com.example.moviesapp.model.dataBase.dataTables.moviesDBModel.MovieDBModel.Companion.NAME
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table


@Table(name = NAME, database = AppDatabase::class, allFields = true)
class MovieDBModel{
    companion object {
        const val NAME = "MovieDBModel"
    }

    @PrimaryKey
    @Column
    var imdbID: String= ""

    @Column
    var title: String = ""

    @Column
    var year: String=""

    @Column
    var rated: String=""

    @Column
    var released: String=""

    @Column
    var runtime: String=""

    @Column
    var genre: String=""

    @Column
    var director: String=""

    @Column
    var writer: String=""

    @Column
    var actors: String=""

    @Column
    var plot: String=""

    @Column
    var language: String=""

    @Column
    var country: String=""

    @Column
    var awards: String=""

    @Column
    var poster: String=""

    @Column
    var metascore: String=""

    @Column
    var imdbRating: String=""

    @Column
    var imdbVotes: String=""

    @Column
    var type: String=""

    @Column
    var DVD: String=""

    @Column
    var boxOffice: String=""

    @Column
    var production: String=""

    @Column
    var website: String=""

    @Column
    var isFavourite: Boolean = false

    constructor() {
        // Mandatory for DBFlow
    }

    constructor(movie: MovieDBObject) {
        movie.let {
            // Currently Using
            imdbID = it.imdbID
            title = it.title
            year = it.year
            plot = it.plot
            poster = it.poster
            imdbRating = it.imdbRating
            isFavourite = it.isFavourite

            // Extra Data (not used currently in UI)
            rated = it.rated
            released = it.released
            runtime = it.runtime
            genre= it.genre
            director = it.director
            writer = it.writer
            actors = it.actors
            language = it.language
            country = it.country
            awards = it.awards
            metascore = it.metascore
            imdbVotes = it.imdbVotes
            type = it.type
            DVD = it.DVD
            boxOffice = it.boxOffice
            production = it.production
            website = it.website
        }
    }

    fun toDomain(): MovieDBObject =
        MovieDBObject(
            imdbID = imdbID,
            title = title,
            year = year,
            plot = plot,
            poster = poster,
            imdbRating = imdbRating,
            isFavourite = isFavourite,

            rated = rated,
            released = released,
            runtime = runtime,
            genre = genre,
            director = director,
            writer = writer,
            actors = actors,
            language = language,
            country = country,
            awards = awards,
            metascore = metascore,
            imdbVotes = imdbVotes,
            type = type,
            DVD = DVD,
            boxOffice = boxOffice,
            production = production,
            website = website
        )
}