package com.example.moviesapp.view.movieDetails

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.databinding.FragmentMovieDetailsBinding
import com.example.moviesapp.viewModel.movieDetails.MovieDetailsViewModel
import com.facebook.drawee.view.SimpleDraweeView
import com.google.android.material.progressindicator.CircularProgressIndicator

class MovieDetailsFragment: Fragment() {
    private var _binding: FragmentMovieDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val moviesCardViewModel =
            ViewModelProvider(this)[MovieDetailsViewModel::class.java]

        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        // Binding Different Views of a layout
        val cardDetailsLayout: ConstraintLayout = binding.cardDetailsLayout
        val moviePoster: SimpleDraweeView = binding.moviePoster
        val movieTitle: TextView = binding.movieTitle
        val releasedYear: TextView = binding.releaseYear
        val imdbRating: TextView = binding.imdbRating
        val plotDescription: TextView = binding.moviePlot
        val isFavouriteButton: ImageView = binding.favouritesButton
        val circularProgress: CircularProgressIndicator = binding.circularProgress

        // Since passing the movie imdbId and Poster data from the movies list fragment
        // Using imdbId to get the entire data for the movie for detailed card showcase
        var imdbID = arguments?.getString("imdbId")
        moviesCardViewModel.moviesSearchByID(imdbID ?: "")

        val imageUri: Uri = Uri.parse(arguments?.getString("poster"))
        moviePoster.setImageURI(imageUri)

        // Using LiveData to update the values of the
        //TODO Create an abstract class to handle all observe functions from each Fragment
        moviesCardViewModel.movieTitle.observe(viewLifecycleOwner){
            movieTitle.text = it
        }
        moviesCardViewModel.releasedYear.observe(viewLifecycleOwner){
            releasedYear.text = it
        }
        moviesCardViewModel.imdbRating.observe(viewLifecycleOwner){
            imdbRating.text = it
        }
        moviesCardViewModel.plotDescription.observe(viewLifecycleOwner){
            plotDescription.text = it
        }

        // Is Favourite Button Functionality
        moviesCardViewModel.isFavouriteButtonImgSource.observe(viewLifecycleOwner){
            isFavouriteButton.setImageResource(it)
        }

        isFavouriteButton.setOnClickListener {
            this.context?.let { it1 -> moviesCardViewModel.favouriteClicked(imdbID ?: "", it1) }
        }

        // For Loading Animation until movie Details Loaded
        moviesCardViewModel.circularProgressVisible.observe(viewLifecycleOwner){
            circularProgress.visibility = it
            isFavouriteButton.visibility = if(it==View.GONE) View.VISIBLE else View.INVISIBLE
            cardDetailsLayout.visibility = if(it==View.GONE) View.VISIBLE else View.INVISIBLE
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}