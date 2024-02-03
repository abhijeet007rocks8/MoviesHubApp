package com.example.moviesapp.adapters

import android.net.Uri
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.model.apiHelpers.dataModels.moviesDataModel.MoviesResponseByNameDataModel
import com.facebook.drawee.view.SimpleDraweeView

class MoviesListRecyclerViewAdapter(val moviesList: List<MoviesResponseByNameDataModel>): RecyclerViewBaseAdapter<MoviesResponseByNameDataModel, MoviesListRecyclerViewAdapter.MoviesListViewHolder>(moviesList){

    override fun getLayout(): Int {
        return R.layout.movie_list_card
    }

    override fun getViewHolder(itemView: View): MoviesListViewHolder {
        return MoviesListViewHolder(itemView, itemClickListener)
    }

    override fun onBindViewHolder(holder: MoviesListViewHolder, position: Int) {
        val currentItem = moviesList[position]
        val imageUri: Uri = Uri.parse(currentItem.Poster)
        holder.moviePoster.setImageURI(imageUri)
        holder.movieTitle.text = currentItem.Title
        holder.showType.text = currentItem.Type
        holder.releaseYear.text = currentItem.Year
    }

    class MoviesListViewHolder(itemView: View, listener: RecyclerViewBaseAdapter.onItemCLickListener): RecyclerView.ViewHolder(itemView){
        val moviePoster: SimpleDraweeView = itemView.findViewById(R.id.movie_poster)
        val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
        val showType: TextView = itemView.findViewById(R.id.show_type)
        val releaseYear: TextView = itemView.findViewById(R.id.release_year)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}