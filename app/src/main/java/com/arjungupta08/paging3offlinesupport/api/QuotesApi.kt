package com.arjungupta08.paging3offlinesupport.api

import com.arjungupta08.paging3.model.QuoteList
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesApi {

    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page : Int) : QuoteList

}