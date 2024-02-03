package com.example.moviesapp.viewModel.movies

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
import com.example.moviesapp.model.apiHelpers.dataModels.moviesDataModel.MovieSearchByNameResponse
import com.example.moviesapp.model.apiHelpers.dataModels.moviesDataModel.MoviesResponseByNameDataModel
import com.example.moviesapp.model.dataBase.dataTables.searchTermDBModel.SearchTermDBFunctions
import kotlinx.coroutines.launch
import retrofit2.Response

class MoviesViewModel: ViewModel() {

    // LiveData Variable for managing the movie list returned by the API
    private val _moviesList = MutableLiveData<ArrayList<MoviesResponseByNameDataModel>>().apply {
        value = ArrayList()
    }
    var moviesList: LiveData<ArrayList<MoviesResponseByNameDataModel>> = _moviesList

    // LiveData Variable for managing the visibility of the Loading Animation
    private val _circularProgressVisible = MutableLiveData<Int>().apply {
        value = View.VISIBLE
    }
    var circularProgressVisible: LiveData<Int> = _circularProgressVisible

//    Function to launch a new Co-routine and make API call to search for the list of movies having the searchTerm in it
    fun moviesSearch(searchQuery:String?, context: Context?){
        val moviesApi = RetroFitObject.getInstance().create(MoviesAPIInterface::class.java)
        // launching a new coroutine
        this.viewModelScope.launch {
            val result: Response<MovieSearchByNameResponse> = moviesApi.getMovieByName(apikey="5081829b",s=searchQuery.toString())

            // Is Success Response pass the List of Data in the Bundle to movies Fragment
            if (result != null && result.body()?.Response == "True") {
                Log.d("Movie", result.body()?.Search.toString())
                _moviesList.value = result.body()?.Search as ArrayList<MoviesResponseByNameDataModel>

                // Add SearchTerm to Recent SearchTerms List if Request if successful
                val searchTermDBFunctionObject = SearchTermDBFunctions()
                searchTermDBFunctionObject.addRecentSearchTermToDB(searchQuery.toString())
            } else {
                // If Failed Response Make a Toast with Error Message
                Toast.makeText(context, result.body()?.Error, Toast.LENGTH_LONG).show()
            }
            // Updating the visibility of the loading animation as the data is loaded at this point
            _circularProgressVisible.value = View.GONE
        }
    }
}
