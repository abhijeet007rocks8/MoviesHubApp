package com.example.moviesapp.model.dataBase.dataTables.searchTermDBModel

import com.example.moviesapp.model.dataBase.AppDatabase
import com.example.moviesapp.model.dataBase.dataTables.searchTermDBModel.SearchTermDBModel.Companion.NAME
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table

@Table(name = NAME, database = AppDatabase::class, allFields = true)
class SearchTermDBModel {
    companion object {
        const val NAME = "SearchTermDBModel"
    }

    @PrimaryKey
    @Column
    var searchTerm: String= ""

    @Column
    var timestamp: Long = 0L

    constructor(){
        // Mandatory for DBFlow
    }

    constructor(term: SearchTermDBObject){
        term.let{
            searchTerm = it.searchTerm
            timestamp = it.timestamp
        }
    }

    fun toDomain(): SearchTermDBObject =
        SearchTermDBObject(
            searchTerm = searchTerm,
            timestamp = timestamp
        )
}