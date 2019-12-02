/*
 * Copyright 2019 vmadalin.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vmadalin.dynamicfeatures.characterslist.ui.list.paging

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource.LoadCallback
import androidx.paging.PageKeyedDataSource.LoadInitialCallback
import androidx.paging.PageKeyedDataSource.LoadInitialParams
import androidx.paging.PageKeyedDataSource.LoadParams
import com.vmadalin.core.network.NetworkState
import com.vmadalin.core.network.repositiories.MarvelRepository
import com.vmadalin.core.network.responses.BaseResponse
import com.vmadalin.core.network.responses.CharacterResponse
import com.vmadalin.dynamicfeatures.characterslist.ui.list.model.CharacterItem
import com.vmadalin.dynamicfeatures.characterslist.ui.list.model.CharacterItemMapper
import com.vmadalin.libraries.testutils.rules.CoroutineRule
import io.mockk.Called
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharactersPageDataSourceTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    interface Callback : () -> Unit

    @MockK(relaxed = true)
    lateinit var repository: MarvelRepository
    @MockK(relaxed = true)
    lateinit var mapper: CharacterItemMapper
    @MockK(relaxed = true)
    lateinit var networkState: MutableLiveData<NetworkState>
    @MockK(relaxed = true)
    lateinit var retry: Callback

    @InjectMockKs(injectImmutable = true, overrideValues = true)
    lateinit var dataSource: CharactersPageDataSource

    private var scope = CoroutineScope(Dispatchers.Main)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun loadInitial_ShouldPostLoadingState() {
        val params = mockk<LoadInitialParams<Int>>()
        val callback = mockk<LoadInitialCallback<Int, CharacterItem>>()
        dataSource.loadInitial(params, callback)

        verify { networkState.postValue(NetworkState.Loading()) }
    }

    @Test
    fun loadInitial_WithError_ShouldPostErrorState() {
        val params = mockk<LoadInitialParams<Int>>()
        val callback = mockk<LoadInitialCallback<Int, CharacterItem>>()
        dataSource.loadInitial(params, callback)

        assertNotNull(retry)
        verify { networkState.postValue(NetworkState.Error()) }
    }

    @Test
    fun loadInitial_WithSuccessEmptyData_ShouldPostEmptySuccessState() {
        val params = LoadInitialParams<Int>(100, false)
        val callback = mockk<LoadInitialCallback<Int, CharacterItem>>(relaxed = true)
        val emptyData = emptyList<CharacterItem>()
        val response = mockk<BaseResponse<CharacterResponse>>()

        coEvery { mapper.map(any()) } returns emptyData
        coEvery { repository.getCharacters(any(), any()) } returns response

        dataSource.loadInitial(params, callback)

        coVerify { repository.getCharacters(0, PAGE_MAX_ELEMENTS) }
        coVerify { mapper.map(response) }
        verify { callback.onResult(emptyData, null, PAGE_MAX_ELEMENTS) }
        verify {
            networkState.postValue(
                NetworkState.Success(
                    isAdditional = false,
                    isEmptyResponse = true
                )
            )
        }
    }

    @Test
    fun loadInitial_WithSuccessData_ShouldPostNonEmptySuccessState() {
        val params = LoadInitialParams<Int>(0, true)
        val callback = mockk<LoadInitialCallback<Int, CharacterItem>>(relaxed = true)
        val data = listOf(mockk<CharacterItem>())
        val response = mockk<BaseResponse<CharacterResponse>>()

        coEvery { mapper.map(any()) } returns data
        coEvery { repository.getCharacters(any(), any()) } returns response

        dataSource.loadInitial(params, callback)

        coVerify { repository.getCharacters(0, PAGE_MAX_ELEMENTS) }
        coVerify { mapper.map(response) }
        verify { callback.onResult(data, null, PAGE_MAX_ELEMENTS) }
        verify { networkState.postValue(NetworkState.Success()) }
    }

    @Test
    fun loadAfter_ShouldPostAdditionalLoadingState() {
        val params = mockk<LoadParams<Int>>()
        val callback = mockk<LoadCallback<Int, CharacterItem>>()
        dataSource.loadAfter(params, callback)

        verify { networkState.postValue(NetworkState.Loading(true)) }
    }

    @Test
    fun loadAfter_WithError_ShouldPostAdditionalErrorState() {
        val params = LoadParams(0, 0)
        val callback = mockk<LoadCallback<Int, CharacterItem>>()
        dataSource.loadAfter(params, callback)

        assertNotNull(retry)
        verify { networkState.postValue(NetworkState.Error(true)) }
    }

    @Test
    fun loadAfter_WithSuccessEmptyData_ShouldPostAdditionalEmptySuccessState() {
        val paramKey = 100
        val params = LoadParams(paramKey, 0)
        val callback = mockk<LoadCallback<Int, CharacterItem>>(relaxed = true)
        val emptyData = emptyList<CharacterItem>()
        val response = mockk<BaseResponse<CharacterResponse>>()

        coEvery { mapper.map(any()) } returns emptyData
        coEvery { repository.getCharacters(any(), any()) } returns response

        dataSource.loadAfter(params, callback)

        coVerify { repository.getCharacters(paramKey, PAGE_MAX_ELEMENTS) }
        coVerify { mapper.map(response) }
        verify { callback.onResult(emptyData, paramKey + PAGE_MAX_ELEMENTS) }
        verify {
            networkState.postValue(
                NetworkState.Success(
                    isAdditional = true,
                    isEmptyResponse = true
                )
            )
        }
    }

    @Test
    fun loadAfter_WithSuccessData_ShouldPostAdditionalNonEmptySuccessState() {
        val paramKey = 0
        val params = LoadParams(paramKey, 0)
        val callback = mockk<LoadCallback<Int, CharacterItem>>(relaxed = true)
        val data = listOf(mockk<CharacterItem>())
        val response = mockk<BaseResponse<CharacterResponse>>()

        coEvery { mapper.map(any()) } returns data
        coEvery { repository.getCharacters(any(), any()) } returns response

        dataSource.loadAfter(params, callback)

        coVerify { repository.getCharacters(paramKey, PAGE_MAX_ELEMENTS) }
        coVerify { mapper.map(response) }
        verify { callback.onResult(data, paramKey + PAGE_MAX_ELEMENTS) }
        verify {
            networkState.postValue(
                NetworkState.Success(
                    isAdditional = true,
                    isEmptyResponse = false
                )
            )
        }
    }

    @Test
    fun loadBefore_ShouldDoNothing() {
        val params = mockk<LoadParams<Int>>()
        val callback = mockk<LoadCallback<Int, CharacterItem>>()
        dataSource.loadBefore(params, callback)

        verify { params wasNot Called }
        verify { callback wasNot Called }
    }
}
