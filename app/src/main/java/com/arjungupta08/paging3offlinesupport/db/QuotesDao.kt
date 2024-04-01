package com.arjungupta08.paging3offlinesupport.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arjungupta08.paging3.model.Result

@Dao
interface QuotesDao {
    @Query("SELECT * FROM Quote")
    fun getQuotes() : PagingSource<Int, Result>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuotes(quotes : List<Result>)

    @Query("DELETE FROM Quote")
    suspend fun deleteQuotes(result: Result)

}