package com.arjungupta08.paging3offlinesupport.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.arjungupta08.paging3offlinesupport.repository.QuotesRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(val quotesRepository: QuotesRepository) : ViewModel() {

    val quoteData = quotesRepository.getQuotes().cachedIn(viewModelScope)

}