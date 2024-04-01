package com.arjungupta08.paging3offlinesupport.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arjungupta08.paging3offlinesupport.db.QuoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesDB(@ApplicationContext context: Context) : QuoteDatabase{
        return Room.databaseBuilder(context, QuoteDatabase::class.java, "quoteDB").build()
    }

}