package com.example.moviesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewBaseAdapter<T,VH: RecyclerView.ViewHolder>(private val itemList: List<T>): RecyclerView.Adapter<VH>(){

    // We pass this <T> as we don't know the what type the List would be but don't want to use Any, So we pass it to this BaseAdapter from child where we implement it and pass the type from there
    lateinit var itemClickListener: onItemCLickListener

    // Interface to store the description on how the ItemClickListener will be implemented
    interface onItemCLickListener{
        fun onItemClick(position: Int)
    }

    // Function to take instance of the `onItemCLickListener` interface and set it as a Item Click functionality
    fun setOnItemClickListener(listener: onItemCLickListener) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView = LayoutInflater.from(parent.context).inflate(
            getLayout(),
            parent, false)
        return getViewHolder(itemView)
    }

    // abstract function to get Layout of item Xml File created in
    abstract fun getLayout(): Int

    // abstract to get the ViewHolder object
    abstract fun getViewHolder(itemView: View): VH

    // abstract to get implementation from the
    abstract override fun onBindViewHolder(holder: VH, position: Int)

    override fun getItemCount(): Int {
        return itemList.size
    }
}
