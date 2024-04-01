package com.arjungupta08.paging3offlinesupport.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arjungupta08.paging3.model.Result
import com.arjungupta08.paging3offlinesupport.model.QuoteRemoteKeys

@Database(entities = [Result::class, QuoteRemoteKeys::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {
    abstract fun quoteDao() : QuotesDao
    abstract fun remoteKeysDao() : RemoteKeysDao
}