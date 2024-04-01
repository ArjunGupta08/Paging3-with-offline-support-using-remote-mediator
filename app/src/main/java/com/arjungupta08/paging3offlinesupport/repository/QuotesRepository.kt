package com.arjungupta08.paging3offlinesupport.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.arjungupta08.paging3offlinesupport.api.QuotesApi
import com.arjungupta08.paging3offlinesupport.paging.QuotePagingSource
import javax.inject.Inject

class QuotesRepository @Inject constructor(val quotesApi: QuotesApi) {

    fun getQuotes() = Pager(
        config = PagingConfig(20, 100),
        pagingSourceFactory = { QuotePagingSource(quotesApi) }
    ).liveData

}