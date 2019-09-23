package com.vmadalin.dynamicfeatures.characterslist.data

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.vmadalin.dynamicfeatures.characterslist.models.CharacterItem
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors
import javax.inject.Inject

class CharactersPageDataSourceFactory
@Inject constructor(
    private val charactersPageDataSource: CharactersPageDataSource
)  {

    @MainThread
    fun test(pageSize: Int): LiveData<PagedList<CharacterItem>> {
        val sourceLiveData = MutableLiveData<CharactersPageDataSource>()
        val sourceFactory = {
            sourceLiveData.postValue(charactersPageDataSource)
            charactersPageDataSource
        }

        // We use toLiveData Kotlin extension function here, you could also use LivePagedListBuilder
        val livePagedList = sourceFactory.toLiveData(
            pageSize = pageSize,
            // provide custom executor for network requests, otherwise it will default to
            // Arch Components' IO pool which is also used for disk access
            fetchDispatcher = Executors.newFixedThreadPool(5).asCoroutineDispatcher()
        )

        return livePagedList
    }
}

