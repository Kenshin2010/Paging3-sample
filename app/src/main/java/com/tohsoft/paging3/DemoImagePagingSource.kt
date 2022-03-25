package com.tohsoft.paging3

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.tohsoft.paging3.DemoImagesRepository.Companion.DEFAULT_PAGE_INDEX
import kotlinx.coroutines.delay
import java.io.IOException

/**
 * provides the data source for paging lib from api calls
 */
@ExperimentalPagingApi
class DemoImagePagingSource() : PagingSource<Int, DemoModel>() {

    /**
     * calls api if there is any error getting results then return the [LoadResult.Error]
     * for successful response return the results using [LoadResult.Page] for some reason if the results
     * are empty from service like in case of no more data from api then we can pass [null] to
     * send signal that source has reached the end of list
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DemoModel> {
        //for first case it will be null, then we can pass some default value, in our case it's 1
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = mutableListOf<DemoModel>()
            for (i in 0..50){
                response.add(DemoModel(System.currentTimeMillis().toString(), "https://cdn2.thedogapi.com/images/Byz6mgqEQ_1280.jpg"))
            }
            delay(3000)
            LoadResult.Page(
                response, prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }

}