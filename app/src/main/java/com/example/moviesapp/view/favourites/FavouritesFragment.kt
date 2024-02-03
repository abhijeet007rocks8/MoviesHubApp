package com.example.moviesapp.view.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.adapters.FavouriteMoviesRecyclerViewAdapter
import com.example.moviesapp.adapters.RecyclerViewBaseAdapter
import com.example.moviesapp.databinding.FragmentFavouritesBinding
import com.example.moviesapp.viewModel.favouites.FavouritesViewModel

class FavouritesFragment : Fragment() {

    private var _binding: FragmentFavouritesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val favouritesViewModel =
            ViewModelProvider(this).get(FavouritesViewModel::class.java)

        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val emptyListMessage: ConstraintLayout = binding.emptyListMessage

        favouritesViewModel.errorMessageShow.observe(viewLifecycleOwner){
            emptyListMessage.visibility=it
        }

        // Recycler View Implementation
        var newRecyclerView = binding.favouritesRecycler
        newRecyclerView.layoutManager = LinearLayoutManager(this.context)
        newRecyclerView.setHasFixedSize(true);

        // Fetching the moviesList from DB which have been favorites
        val movieList = favouritesViewModel.getFavouriteMovieList()

        // Modified Adapter
        val recyclerViewHandler = FavouriteMoviesRecyclerViewAdapter(favouriteMovies = movieList)
        newRecyclerView.adapter = recyclerViewHandler
        recyclerViewHandler.setOnItemClickListener(object: RecyclerViewBaseAdapter.onItemCLickListener{
            override fun onItemClick(position: Int) {
                val bundle: Bundle = bundleOf(
                    "poster" to movieList[position].poster,
                    "imdbId" to movieList[position].imdbID,
                )
                view?.let {
                    Navigation.findNavController(it).navigate(
                        R.id.nav_favourites_to_nav_movie_details,
                        bundle
                    )
                }
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}