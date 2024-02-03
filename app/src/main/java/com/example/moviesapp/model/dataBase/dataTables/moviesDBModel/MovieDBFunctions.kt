package com.example.moviesapp.model.dataBase.dataTables.moviesDBModel

import com.example.moviesapp.model.apiHelpers.dataModels.moviesDataModel.MoviesResponseByIDDataModel

class MovieDBFunctions {

    // MoviesDB DAO class Object
    val movieDBOperationsObject = MovieDBOperations();

    // Repository Functions handling the extraction/insertion of Required data from Data Sources

    // Function to get the MovieDetails Data from the Network Call and push it to DB
    fun InsertMovieInDB(resultData: MoviesResponseByIDDataModel?){
        if(resultData?.imdbID != null) {
            val movie = MovieDBObject(
                imdbID = resultData.imdbID,
                title = resultData.Title.toString(),
                year = resultData.Year.toString(),
                plot = resultData.Plot.toString(),
                poster = resultData.Poster.toString(),
                imdbRating = resultData.imdbRating.toString(),
                isFavourite = false,
                rated = resultData.Rated.toString(),
                released = resultData.Released.toString(),
                runtime = resultData.Runtime.toString(),
                genre = resultData.Genre.toString(),
                director = resultData.Director.toString(),
                writer = resultData.Writer.toString(),
                actors = resultData.Actors.toString(),
                language = resultData.Language.toString(),
                country = resultData.Country.toString(),
                awards = resultData.Awards.toString(),
                metascore = resultData.Metascore.toString(),
                imdbVotes = resultData.imdbVotes.toString(),
                type = resultData.Type.toString(),
                DVD = resultData.DVD.toString(),
                boxOffice = resultData.BoxOffice.toString(),
                production = resultData.Production.toString(),
                website = resultData.Website.toString()
            )

            movieDBOperationsObject.addMovieToDB(movie)
        }
    }

    // Function to get a particular Movie Detail from the DB (Data Source)
    fun getMovieDetailsByID(imdbID: String): List<MovieDBObject>{
        return movieDBOperationsObject.getMovieByIDFromDB(imdbID)
    }

    // Function to update the isFavourite State Movie Detail from the DB (Data Source)
    fun updateIsFavouriteOfMovieByID(isFavourite: Boolean, imdbID: String){
        movieDBOperationsObject.updateIsFavouriteInDB(isFavourite, imdbID)
    }

    fun getAllFavouriteMovies(): List<MovieDBObject> {
        return movieDBOperationsObject.getFavouriteMovies()
    }
}