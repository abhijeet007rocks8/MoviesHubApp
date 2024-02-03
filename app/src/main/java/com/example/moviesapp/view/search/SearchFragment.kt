package com.example.moviesapp.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.adapters.SearchTermRecyclerViewAdapter
import com.example.moviesapp.model.dataBase.dataTables.searchTermDBModel.SearchTermDBObject
import com.example.moviesapp.databinding.FragmentSearchBinding
import com.example.moviesapp.adapters.RecyclerViewBaseAdapter
import com.example.moviesapp.viewModel.search.SearchViewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val searchViewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Binding Different Views of a layout
        val editText: EditText = binding.movieSearchBar
        val searchButton: ImageButton = binding.searchButton
        val textView: TextView = binding.textHome

        // Recycler View Implementation (Binding and Initialization)
        var newRecyclerView = binding.searchtermRecycler
        newRecyclerView.layoutManager = LinearLayoutManager(this.context)
        newRecyclerView.setHasFixedSize(true)

        // Fetching the moviesList from DB which have been favorites
        var searchTermList = searchViewModel.getSearchTermsList()

        // For the case of first load add few suggestions (Not from Cache) (Hard Coded)
        if(searchTermList.isEmpty()) {
            val defaultSearchTerm1 = SearchTermDBObject("Mission Impossible", 0);
            val defaultSearchTerm2 = SearchTermDBObject("Indiana Jones", 0)
            searchTermList+=defaultSearchTerm1
            searchTermList+=defaultSearchTerm2
        }

        // Handler class object which creates object to handle abstract functions too
        var recyclerViewHandler = SearchTermRecyclerViewAdapter(searchList = searchTermList)

        // Assigning adapter to recyclerView binder
        newRecyclerView.adapter = recyclerViewHandler

        // RecyclerView items onclick listener
        recyclerViewHandler.setOnItemClickListener(object: RecyclerViewBaseAdapter.onItemCLickListener{
            override fun onItemClick(position: Int) {
                searchViewModel.fillSearchTermInEditBar(searchTermList[position].searchTerm)
                searchButton.performClick()
            }
        })

        // This is the Example of using LiveData and SingleLiveEvent
        searchViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        searchViewModel.movieName.observe(viewLifecycleOwner) {
            editText.setText(searchViewModel.movieName.value)
        }

        searchButton.setOnClickListener {
            // This is a function from ViewModel that takes the current state of the
            // editText value and updated it to the text to update it
            this.context?.let { context -> searchViewModel.searchMovie(editText.text.toString(), it, context) }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}