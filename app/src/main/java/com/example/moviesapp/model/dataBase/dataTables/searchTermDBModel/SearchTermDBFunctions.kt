package com.example.moviesapp.model.dataBase.dataTables.searchTermDBModel

class SearchTermDBFunctions {

    // SearchTermDB DAO class Object
    var searchTermDBOperationObject: SearchTermDBOperations = SearchTermDBOperations();

    // Repository Function handling the extraction/insertion of required data from Data Sources

    // Function to handle the insertion of Recent Search Term in the DB (Data Source Layer)
    fun addRecentSearchTermToDB(searchTerm: String): Unit{
        checkTermInDBAndAdd(searchTerm)
    }

    //    function to check whether searchTerm is already present in the DB and if not call the function to add it to DB
    private fun checkTermInDBAndAdd(searchTerm: String): Unit{
        val termPresentInDB = searchTermDBOperationObject.getParticularSearchTermFromDB(searchTerm)
        if(termPresentInDB.size <= 0) searchTermDBOperationObject.addSearchTermToDB(searchTerm)
        else searchTermDBOperationObject.updateSearchTermToDB(searchTerm)
    }

    // Function to get all the SearchTerms from the DB (Data Source Layer)
    fun getAllSearchTermsFromDB(): List<SearchTermDBObject> {
        return searchTermDBOperationObject.getSearchTermsList();
    }
}