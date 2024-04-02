package com.arjungupta08.paging3offlinesupport.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.arjungupta08.paging3.model.Result
import com.arjungupta08.paging3offlinesupport.api.QuotesApi
import com.arjungupta08.paging3offlinesupport.db.QuoteDatabase
import com.arjungupta08.paging3offlinesupport.model.QuoteRemoteKeys

@OptIn(ExperimentalPagingApi::class)
class QuoteRemoteMediator (
    private val quotesApi: QuotesApi,
    private val quoteDatabase: QuoteDatabase
) : RemoteMediator<Int, Result>() {

    val quotesDao = quoteDatabase.quoteDao()
    val quotesRemoteKeys = quoteDatabase.remoteKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Result>): MediatorResult {

        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeysClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeysForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeysForLastItem(state)
                    val nextPage = remoteKeys?.nextPage?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }
            }

            val response = quotesApi.getQuotes(currentPage)
            val endOfPaginationReached = response.totalPages == currentPage

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            quoteDatabase.withTransaction {

                if (loadType == LoadType.REFRESH) {
                    quotesDao.deleteQuotes()
                    quotesRemoteKeys.deleteAllRemoteKeys()
                }

                quotesDao.addQuotes(response.results)
                val keys = response.results.map { quote ->
                    QuoteRemoteKeys(
                        id = quote._id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                quotesRemoteKeys.addAllRemoteKeys(quoteRemoteKeys = keys)
            }
            MediatorResult.Success(endOfPaginationReached)

        } catch (e : Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeysClosestToCurrentPosition(
        state: PagingState<Int, Result>
    ) : QuoteRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?._id?.let { id ->
                quotesRemoteKeys.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeysForFirstItem(
        state: PagingState<Int, Result>
    ) : QuoteRemoteKeys? {
        return state.pages.firstOrNull() { it.data.isNotEmpty() } ?.data?.firstOrNull()?.let {quote ->
            quotesRemoteKeys.getRemoteKeys(quote._id)
        }
    }

    private suspend fun getRemoteKeysForLastItem(
        state: PagingState<Int, Result>
    ) : QuoteRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() } ?.data?.lastOrNull()?.let {quote ->
            quotesRemoteKeys.getRemoteKeys(quote._id)
        }
    }
}