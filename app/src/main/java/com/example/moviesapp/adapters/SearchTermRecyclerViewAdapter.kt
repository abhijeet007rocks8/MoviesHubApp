package com.example.moviesapp.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.model.dataBase.dataTables.searchTermDBModel.SearchTermDBObject

class SearchTermRecyclerViewAdapter(val searchList: List<SearchTermDBObject>): RecyclerViewBaseAdapter<SearchTermDBObject, SearchTermRecyclerViewAdapter.SearchTermViewHolder>(searchList) {

    override fun getLayout(): Int {
        return R.layout.searchterm_card
    }

    override fun getViewHolder(itemView: View): SearchTermViewHolder {
        return SearchTermViewHolder(itemView, itemClickListener)
    }

    override fun onBindViewHolder(holder: SearchTermViewHolder, position: Int) {
        val currentItem = searchList[position]
        holder.searchTerm.text = currentItem.searchTerm
    }

    // Changing for each handler based on the details
    class SearchTermViewHolder(itemView: View, listener: onItemCLickListener) : RecyclerView.ViewHolder(itemView) {
        val searchTerm: TextView = itemView.findViewById(R.id.search_term)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

}