package com.tohsoft.paging3

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

/**
 * repository class to manage the data flow and map it if needed
 */
@ExperimentalPagingApi
class DemoImagesRepository() {

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 20

        //get doggo repository instance
        fun getInstance() = DemoImagesRepository()
    }


    fun letDemoImagesFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<DemoModel>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { DemoImagePagingSource() }
        ).flow
    }

    /**
     * let's define page size, page size is the only required param, rest is optional
     */
    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }
}