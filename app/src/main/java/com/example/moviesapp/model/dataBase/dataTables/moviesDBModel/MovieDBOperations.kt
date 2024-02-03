package com.example.moviesapp.model.dataBase.dataTables.moviesDBModel

import android.util.Log
import com.example.moviesapp.model.dataBase.dataTables.moviesDBModel.MovieDBModel_Table
import com.raizlabs.android.dbflow.kotlinextensions.insert
import com.raizlabs.android.dbflow.sql.language.SQLite

class MovieDBOperations {

    // DB handler function to handle the to get all the posts
    fun getAllMovies(): List<MovieDBObject> =
        SQLite.select()
            .from(MovieDBModel::class.java)
            .queryList()
            .map { it.toDomain() }

    //    DB handling Function for updating the state of isFavourite for the current Movie
    fun updateIsFavouriteInDB(updatedIsFavouriteState: Boolean, imdbID: String) =
        SQLite.update(MovieDBModel::class.java)
            .set(MovieDBModel_Table.isFavourite.eq(updatedIsFavouriteState))
            .where(MovieDBModel_Table.imdbID.`is`(imdbID))
            .async()
            .execute()

    //    DB handling function for fetching Data by Movie ID
    fun getMovieByIDFromDB(imdbID: String): List<MovieDBObject> =
        SQLite.select()
            .from(MovieDBModel::class.java)
            .where(MovieDBModel_Table.imdbID.`is`(imdbID))
            .queryList()
            .map { it.toDomain() }

    //    DB handling function for inserting movieData
    fun addMovieToDB(movie: MovieDBObject) {
        val inserted = MovieDBModel(movie).insert()
        Log.d("Inserted in DB", inserted.toString())
    }

    //    DB Handling Function to fetch the favourite movies
    fun getFavouriteMovies(): List<MovieDBObject> =
        SQLite.select()
            .from(MovieDBModel::class.java)
            .where(MovieDBModel_Table.isFavourite.`is`(true))
            .queryList()
            .map { it.toDomain() }
}