package com.tohsoft.paging3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@ExperimentalPagingApi
class MainActivity : AppCompatActivity() {

    lateinit var rvDemo: RecyclerView
    lateinit var demoViewModel: DemoViewModel
    lateinit var adapter: DemoImageAdapter
    lateinit var demoStateAdapter: DemoStateAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initMembers()
        setUpViews()
        fetchDemoImages()
    }

    private fun fetchDemoImages() {
        lifecycleScope.launch {
            demoViewModel.fetDemoImage().distinctUntilChanged().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun initMembers() {
        demoViewModel = defaultViewModelProviderFactory.create(DemoViewModel::class.java)
        adapter = DemoImageAdapter()
        demoStateAdapter = DemoStateAdapter { adapter.retry() }
    }

    private fun setUpViews() {
        rvDemo = findViewById(R.id.rvDemo)
        rvDemo.layoutManager = GridLayoutManager(this, 2)
        rvDemo.adapter = adapter.withLoadStateFooter(demoStateAdapter)
    }
}