package com.example.moviesapp.viewModel.movieDetails

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.model.apiHelpers.apiHelpers.MoviesAPIInterface
import com.example.moviesapp.model.apiHelpers.apiHelpers.RetroFitObject
import com.example.moviesapp.model.apiHelpers.dataModels.moviesDataModel.MoviesResponseByIDDataModel
import com.example.moviesapp.model.dataBase.dataTables.moviesDBModel.MovieDBFunctions
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieDetailsViewModel: ViewModel() {
    //    LiveData for the movieName enter by the User
    private val _text = MutableLiveData<String>().apply {
        value = "This is Movie Details Fragment"
    }
    var text: LiveData<String> = _text;

//    LiveData for all the Params of the Movie Details Fragment UI elements
    private val _movieTitle = MutableLiveData<String>().apply { value = "" }
    val movieTitle: LiveData<String> = _movieTitle

    private val _releasedYear = MutableLiveData<String>().apply { value = "" }
    val releasedYear: LiveData<String> = _releasedYear

    private val _imdbRating = MutableLiveData<String>().apply { value = "" }
    val imdbRating: LiveData<String> = _imdbRating

    private val _plotDescription = MutableLiveData<String>().apply { value = "" }
    val plotDescription: LiveData<String> = _plotDescription

    private val _isFavouriteButtonImgSource = MutableLiveData<Int>().apply { value = android.R.drawable.btn_star_big_off }
    val isFavouriteButtonImgSource: LiveData<Int> = _isFavouriteButtonImgSource

    private val _circularProgressVisible = MutableLiveData<Int>().apply {
        value = View.VISIBLE
    }
    var circularProgressVisible: LiveData<Int> = _circularProgressVisible

    // DB lookup for the movie imdbID if present in cache or not
    // (could have been abstracted to Model layer to handle movieData fetch by imdbID)
    fun moviesSearchByID(imdbID:  String){
        val moviesDBFunctionsObject = MovieDBFunctions()
        val movieSearchInDB = moviesDBFunctionsObject.getMovieDetailsByID(imdbID)

        Log.d("Data Fetch from DB", movieSearchInDB.size.toString())

        if(movieSearchInDB.size <= 0){
            fetchMovieDataByNetwork(imdbID)
            return
        }
        val movieData = movieSearchInDB[0]

        Log.d("Data Fetch from DB", movieData.isFavourite.toString())

        _movieTitle.value = movieData.title
        _imdbRating.value = movieData.imdbRating
        _releasedYear.value = movieData.year
        _plotDescription.value = movieData.plot
        if (movieData.isFavourite) _isFavouriteButtonImgSource.value = android.R.drawable.btn_star_big_on
        _circularProgressVisible.value = View.GONE
    }

    private fun fetchMovieDataByNetwork(imdbID:  String){
        Log.d("Data Fetch", "from Network")
        val moviesApi = RetroFitObject.getInstance().create(MoviesAPIInterface::class.java)
        this.viewModelScope.launch {
            val result: Response<MoviesResponseByIDDataModel> = moviesApi.getMovieByID(apikey="5081829b",i=imdbID)
            if(result != null){
                val resultData = result.body()
                Log.d("resultData", result.body().toString())
                _movieTitle.value = resultData?.Title
                _imdbRating.value = resultData?.imdbRating
                _releasedYear.value = resultData?.Year
                _plotDescription.value = resultData?.Plot

                val moviesDBFunctionsObject = MovieDBFunctions()
                // Since New Movie Searched so add to DB
                moviesDBFunctionsObject.InsertMovieInDB(resultData)
            }
            _circularProgressVisible.value = View.GONE
        }
    }

//    function to handle favourite Toggle
    fun favouriteClicked(imdbID: String, context: Context){
        // Add functionality to switch the Icon based on isFavourite is True or Not
        val isFavouriteMovieCurrentState = (_isFavouriteButtonImgSource.value == android.R.drawable.btn_star_big_on)
        toggleFavouriteForMovie(isFavouriteMovieCurrentState, imdbID, context)
    }

    private fun toggleFavouriteForMovie(isFavouriteMovieCurrentState: Boolean, imdbID: String, context: Context){
        val moviesDBFunctionsObject = MovieDBFunctions()
        moviesDBFunctionsObject.updateIsFavouriteOfMovieByID(!isFavouriteMovieCurrentState, imdbID)
        if(isFavouriteMovieCurrentState){
            _isFavouriteButtonImgSource.value = android.R.drawable.btn_star_big_off
        }else{
            _isFavouriteButtonImgSource.value = android.R.drawable.btn_star_big_on
        }
        val toastMessage: String = if (isFavouriteMovieCurrentState) "Removed from Favourites" else "Added to Favourites"
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
    }
}