package com.arjungupta08.paging3offlinesupport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arjungupta08.paging3offlinesupport.paging.LoaderAdapter
import com.arjungupta08.paging3offlinesupport.paging.QuotePagingAdapter
import com.arjungupta08.paging3offlinesupport.viewModel.MainViewModel
import com.arjungupta08.paging3offlinesupport.viewModel.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private lateinit var adapter: QuotePagingAdapter
    private val recyclerView : RecyclerView
        get() = findViewById(R.id.recyclerView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = QuotePagingAdapter()
        viewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )

        viewModel.quoteData.observe(this) {
            adapter.submitData(lifecycle, it)
        }

    }
}