package com.arjungupta08.paging3offlinesupport.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuoteRemoteKeys(

    @PrimaryKey(autoGenerate = false)
    val id : String,

    val prevPage : Int?,
    val nextPage : Int?
)
