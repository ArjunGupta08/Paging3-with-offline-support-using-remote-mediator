package com.arjungupta08.paging3offlinesupport.di

import com.arjungupta08.paging3offlinesupport.api.QuotesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    companion object {
        const val BASE_URL = "https://api.quotable.io"
    }

    @Singleton
    @Provides
    fun providesRetrofit() : Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun providesApi(retrofit: Retrofit) : QuotesApi {
        return retrofit.create(QuotesApi::class.java)
    }

}