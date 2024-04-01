package com.arjungupta08.paging3offlinesupport.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arjungupta08.paging3offlinesupport.model.QuoteRemoteKeys


@Dao
interface RemoteKeysDao {

    @Query("SELECT * FROM QuoteRemoteKeys where id=:id")
    suspend fun getRemoteKeys(id : String) : QuoteRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(quoteRemoteKeys: List<QuoteRemoteKeys>)

    @Query("DELETE FROM QuoteRemoteKeys")
    suspend fun deleteAllRemoteKeys()

}