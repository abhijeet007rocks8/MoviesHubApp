package com.example.moviesapp.viewModel.favouites

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.model.dataBase.dataTables.moviesDBModel.MovieDBFunctions
import com.example.moviesapp.model.dataBase.dataTables.moviesDBModel.MovieDBObject

class FavouritesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Favourites Fragment"
    }
    val text: LiveData<String> = _text

    private val _errorMessageShow = MutableLiveData<Int>().apply {
        value = View.GONE
    }
    val errorMessageShow: LiveData<Int> = _errorMessageShow

    // Function to fetch all the Favourite Movies from Repository Layer
    fun getFavouriteMovieList(): List<MovieDBObject>{
        val movieDBFunctionsObject = MovieDBFunctions()
        var favouritesList = movieDBFunctionsObject.getAllFavouriteMovies()
        _errorMessageShow.value=View.GONE
        if(favouritesList.isEmpty()) _errorMessageShow.value=View.VISIBLE
        return favouritesList
    }
}