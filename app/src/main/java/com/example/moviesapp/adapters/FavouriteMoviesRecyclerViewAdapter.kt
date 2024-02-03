package com.example.moviesapp.adapters

import android.net.Uri
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.model.dataBase.dataTables.moviesDBModel.MovieDBObject
import com.facebook.drawee.view.SimpleDraweeView

class FavouriteMoviesRecyclerViewAdapter(var favouriteMovies:  List<MovieDBObject>):
    RecyclerViewBaseAdapter<MovieDBObject, FavouriteMoviesRecyclerViewAdapter.FavouriteMovieViewHolder>(favouriteMovies) {

    override fun getLayout(): Int {
        return R.layout.favourite_movie_cards
    }

    override fun getViewHolder(itemView: View): FavouriteMovieViewHolder {
        return FavouriteMovieViewHolder(itemView, itemClickListener)
    }

    override fun onBindViewHolder(holder: FavouriteMovieViewHolder, position: Int) {
        val currentItem = favouriteMovies[position]
        val imageUri: Uri = Uri.parse(currentItem.poster)
        holder.moviePoster.setImageURI(imageUri)
        holder.movieTitle.text = currentItem.title
    }

    class FavouriteMovieViewHolder(itemView: View, listener: onItemCLickListener): RecyclerView.ViewHolder(itemView) {
        val moviePoster: SimpleDraweeView = itemView.findViewById(R.id.movie_poster)
        val movieTitle: TextView = itemView.findViewById(R.id.movie_title)

        init{
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}