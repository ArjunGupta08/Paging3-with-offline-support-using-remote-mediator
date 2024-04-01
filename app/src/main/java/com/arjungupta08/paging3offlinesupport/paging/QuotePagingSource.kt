package com.arjungupta08.paging3offlinesupport.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arjungupta08.paging3.model.Result
import com.arjungupta08.paging3offlinesupport.api.QuotesApi
import java.lang.Exception

class QuotePagingSource(private val quotesApi: QuotesApi) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val position = params.key ?: 1
            val response = quotesApi.getQuotes(position)
            LoadResult.Page(
                data = response.results,
                prevKey = if (position == 1) null else position-1,
                nextKey = if (position == response.totalPages) null else position+1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}