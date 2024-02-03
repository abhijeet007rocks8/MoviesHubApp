package com.example.moviesapp.viewModel.search

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation.findNavController
import com.example.moviesapp.R
import com.example.moviesapp.model.dataBase.dataTables.searchTermDBModel.SearchTermDBFunctions
import com.example.moviesapp.model.dataBase.dataTables.searchTermDBModel.SearchTermDBObject
import com.example.moviesapp.utilities.NetworkStatusCheck


class SearchViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Welcome to Movie Hub"
    }

    // Used to reset the value of the EditText after each search
    private val _movieName = MutableLiveData<String>().apply {
        value = ""
    }

    // LiveData for the movieName enter by the User
    var movieName: LiveData<String> = _movieName
    var text: LiveData<String> = _text

    // Function to get SearchTermsList from the Cache (Wrapper on DB Call)
    fun getSearchTermsList(): List<SearchTermDBObject>{
        val searchTermDBFunctionObject = SearchTermDBFunctions()
        _text.value = "Welcome to Movie Hub"
        return searchTermDBFunctionObject.getAllSearchTermsFromDB()
    }

    // SearchTerm Click Listener to populate in the EditText
    fun fillSearchTermInEditBar(searchTerm: String){
        _movieName.value=searchTerm
    }

    // Function to handle the searchClick and call other function based on tasks to do
    fun searchMovie(searchQuery: String, view: View, context: Context){
        _movieName.value = ""
        if(searchQuery.trim().isNotBlank()) {
            // If no internet Connection terminate request and give a Toast message
            var isNetworkAvailable = checkInternetIsAvailable(context)
            if(!isNetworkAvailable) {
                Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show()
            }else {
                _text.value = "Searching for " + searchQuery.trim() + " ... "
                moviesSearch(searchQuery.trim(), view)
            }
        }else{
            Toast.makeText(context, "Kindly enter a Movie name to Search", Toast.LENGTH_SHORT).show()
        }
    }

    // Function to pass the movieSearchTerm to movies Fragment
    private fun moviesSearch(searchQuery: String, view: View){
        val movieSearchBundle: Bundle = bundleOf(
            "SearchQuery" to searchQuery
        )
        findNavController(view).navigate(
            R.id.nav_search_to_nav_movie,
            movieSearchBundle
        )
    }

    // Function to check whether device has Internet access (true) or Not (false)
    private fun checkInternetIsAvailable(context: Context): Boolean{
        val networkStatusCheckObject = NetworkStatusCheck()
        return networkStatusCheckObject.checkInternetIsAvailable(context)
    }
}
