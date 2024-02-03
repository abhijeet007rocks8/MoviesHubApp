package com.example.moviesapp.model.dataBase.dataTables.searchTermDBModel

import android.util.Log
import com.raizlabs.android.dbflow.kotlinextensions.insert
import com.raizlabs.android.dbflow.sql.language.SQLite
import java.sql.Timestamp
import java.time.Instant

class SearchTermDBOperations {

    //    DB Handling Function to fetch the Recent Searches
    fun getSearchTermsList(): List<SearchTermDBObject> =
        SQLite.select()
            .from(SearchTermDBModel::class.java)
            .orderBy(SearchTermDBModel_Table.timestamp, false)
            .queryList()
            .map { it.toDomain() }


    //    DB handling function to get all the searchTerms from the DB having similar value to searchTerm
    fun getParticularSearchTermFromDB(searchTerm: String): List<SearchTermDBObject> =
        SQLite.select()
            .from(SearchTermDBModel::class.java)
            .where(SearchTermDBModel_Table.searchTerm.`is`(searchTerm))
            .queryList()
            .map { it.toDomain() }

    //    DB handling function to push the searchTerm to DB if not stored already
    fun addSearchTermToDB(searchTerm: String){
        val searchTermObject = SearchTermDBObject(searchTerm, Timestamp.from(Instant.now()).time)
        val inserted = SearchTermDBModel(searchTermObject).insert()
        Log.d("Inserted in DB", inserted.toString())
    }

    //    DB handling function to update the timestamp for searchTerm to DB if stored already
    fun updateSearchTermToDB(searchTerm: String) =
        SQLite.update(SearchTermDBModel::class.java)
            .set(SearchTermDBModel_Table.timestamp.eq(Timestamp.from(Instant.now()).time))
            .where(SearchTermDBModel_Table.searchTerm.`is`(searchTerm))
            .async()
            .execute()
}