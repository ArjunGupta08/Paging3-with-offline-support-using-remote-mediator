package com.arjungupta08.paging3offlinesupport.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.RemoteMediator
import androidx.paging.liveData
import com.arjungupta08.paging3offlinesupport.api.QuotesApi
import com.arjungupta08.paging3offlinesupport.db.QuoteDatabase
import com.arjungupta08.paging3offlinesupport.paging.QuotePagingSource
import com.arjungupta08.paging3offlinesupport.paging.QuoteRemoteMediator
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class QuotesRepository @Inject constructor(
    val quotesApi: QuotesApi,
    val quoteDatabase: QuoteDatabase
) {

    fun getQuotes() = Pager(
        config = PagingConfig(20, 100),
        remoteMediator = QuoteRemoteMediator(quotesApi, quoteDatabase),
        pagingSourceFactory = { quoteDatabase.quoteDao().getQuotes() }
    ).liveData

}