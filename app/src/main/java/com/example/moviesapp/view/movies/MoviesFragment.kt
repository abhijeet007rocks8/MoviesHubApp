package com.example.moviesapp.view.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.adapters.MoviesListRecyclerViewAdapter
import com.example.moviesapp.adapters.RecyclerViewBaseAdapter
import com.example.moviesapp.model.apiHelpers.dataModels.moviesDataModel.MoviesResponseByNameDataModel
import com.example.moviesapp.databinding.FragmentMovieBinding
import com.example.moviesapp.viewModel.movies.MoviesViewModel
import com.google.android.material.progressindicator.CircularProgressIndicator

class MoviesFragment: Fragment() {
    private var _binding: FragmentMovieBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var movieList: ArrayList<MoviesResponseByNameDataModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val moviesViewModel =
            ViewModelProvider(this)[MoviesViewModel::class.java]

        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Handling for the showcase of LoadingIndicator until API call is completed
        val circularProgress: CircularProgressIndicator = binding.circularProgress

        moviesViewModel.circularProgressVisible.observe(viewLifecycleOwner){
            circularProgress.visibility = it
        }

        // Recycler View Implementation
        val newRecyclerView = binding.moviesRecycler
        newRecyclerView.layoutManager = LinearLayoutManager(this.context)
        newRecyclerView.setHasFixedSize(true)

        // Accessing the bundle data passed from the SearchFragment
        val searchTerm = arguments?.getString("SearchQuery")

        // To stop the bug of scrolling to top on coming back to this fragment (Bandage Fix)
        // TODO Handle this to manage the last adapter list location
        if(movieList.isEmpty()) moviesViewModel.moviesSearch(searchTerm, this.context)

        moviesViewModel.moviesList.observe(viewLifecycleOwner) { it ->
            movieList = it

        //  As many time the movieList changes it should Re render the Recycler View
            val moviesListRecyclerViewAdapter = MoviesListRecyclerViewAdapter(movieList)
            newRecyclerView.adapter = moviesListRecyclerViewAdapter
            moviesListRecyclerViewAdapter.setOnItemClickListener(object: RecyclerViewBaseAdapter.onItemCLickListener{
                override fun onItemClick(position: Int) {
                    Log.d("imdbID Passed", movieList[position].imdbID.toString())
                    val bundle: Bundle = bundleOf(
                        "imdbId" to movieList[position].imdbID.toString(),
                        "poster" to movieList[position].Poster,
                    )
                    view?.let {
                        Navigation.findNavController(it).navigate(
                            R.id.nav_movie_to_nav_movie_details,
                            bundle
                        )
                    }
                }
            })
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}