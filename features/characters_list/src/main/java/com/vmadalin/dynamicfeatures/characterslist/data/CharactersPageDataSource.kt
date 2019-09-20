package com.vmadalin.dynamicfeatures.characterslist.data

import androidx.paging.PageKeyedDataSource
import com.vmadalin.core.network.repositiories.MarvelRepository
import com.vmadalin.core.network.responses.BaseResponse
import com.vmadalin.core.network.responses.CharacterResponse
import com.vmadalin.dynamicfeatures.characterslist.models.CharacterItem
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

const val PAGE_INIT_ELEMENTS = 0
const val PAGE_MAX_ELEMENTS = 50

class CharactersPageDataSource @Inject constructor(
    private val scope: CoroutineScope,
    private val repository: MarvelRepository
) : PageKeyedDataSource<Int, CharacterItem>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CharacterItem>
    ) {
        scope.launch(getJobErrorHandler()) {
            val result = repository.getCharacters(PAGE_INIT_ELEMENTS, PAGE_MAX_ELEMENTS)
            callback.onResult(getCharacterItems(result), null, 1)
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CharacterItem>
    ) {
        scope.launch(getJobErrorHandler()) {
            val result = repository.getCharacters(params.key, params.requestedLoadSize)
            callback.onResult(getCharacterItems(result), params.key + 1)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CharacterItem>
    ) {
        scope.launch(getJobErrorHandler()) {
            val result = repository.getCharacters(params.key, params.requestedLoadSize)
            callback.onResult(getCharacterItems(result), params.key - 1)
        }
    }

    private fun getCharacterItems(response: BaseResponse<CharacterResponse>): List<CharacterItem> {
        return response.data.results.map {
            CharacterItem(
                id = it.id,
                name = it.name,
                description = it.description,
                imageUrl = ""
            )
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        Timber.e("An error happened: ${e.message}")
    }
}
