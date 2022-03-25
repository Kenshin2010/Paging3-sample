package com.tohsoft.paging3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class DemoViewModel(val repository: DemoImagesRepository = DemoImagesRepository.getInstance()) :
    ViewModel() {

    /**
     * returning non modified PagingData<DoggoImageModel> value as opposite to remote view model
     * where we have mapped the coming values into different object
     */
    fun fetDemoImage(): Flow<PagingData<DemoModel>> {
        return repository.letDemoImagesFlow().cachedIn(viewModelScope)
    }
}