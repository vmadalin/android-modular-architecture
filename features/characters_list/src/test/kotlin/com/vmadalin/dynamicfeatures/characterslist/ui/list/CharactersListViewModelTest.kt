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

package com.vmadalin.dynamicfeatures.characterslist.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.vmadalin.core.network.NetworkState
import com.vmadalin.dynamicfeatures.characterslist.ui.list.paging.CharactersPageDataSource
import com.vmadalin.dynamicfeatures.characterslist.ui.list.paging.CharactersPageDataSourceFactory
import com.vmadalin.libraries.testutils.rules.CoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharactersListViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK(relaxed = true)
    lateinit var dataSourceFactory: CharactersPageDataSourceFactory
    @MockK(relaxed = true)
    lateinit var stateObserver: Observer<CharactersListViewState>
    @MockK(relaxed = true)
    lateinit var eventObserver: Observer<CharactersListViewEvent>
    lateinit var viewModel: CharactersListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun networkSuccessEmptyCharacters_ShouldBeEmptyState() {
        val networkState = NetworkState.Success(
            isEmptyResponse = true
        )
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<CharactersPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = CharactersListViewState.Empty
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun networkSuccessAdditionalEmptyCharacters_ShouldBeNoMoreElementsState() {
        val networkState = NetworkState.Success(
            isAdditional = true,
            isEmptyResponse = true
        )
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<CharactersPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = CharactersListViewState.NoMoreElements
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun networkSuccessAdditionalCharacters_ShouldBeLoadedState() {
        val networkState = NetworkState.Success(
            isAdditional = true
        )
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<CharactersPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = CharactersListViewState.Loaded
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun networkSuccessCharacters_ShouldBeLoadedState() {
        val networkState = NetworkState.Success()
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<CharactersPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = CharactersListViewState.Loaded
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun networkLoadingCharacters_ShouldBeLoadingState() {
        val networkState = NetworkState.Loading()
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<CharactersPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = CharactersListViewState.Loading
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun networkAdditionalLoadingCharacters_ShouldBeAddLoadingState() {
        val networkState = NetworkState.Loading(
            isAdditional = true
        )
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<CharactersPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = CharactersListViewState.AddLoading
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun networkErrorCharacters_ShouldBeErrorState() {
        val networkState = NetworkState.Error()
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<CharactersPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = CharactersListViewState.Error
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun networkAdditionalErrorCharacters_ShouldBeAddErrorState() {
        val networkState = NetworkState.Error(true)
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<CharactersPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = CharactersListViewState.AddError
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun refreshCharacterList_ShouldInvokeDataSourceMethod() {
        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.refreshLoadedCharactersList()

        verify { dataSourceFactory.refresh() }
        verify(exactly = 0) { dataSourceFactory.retry() }
    }

    @Test
    fun retryCharacterList_ShouldInvokeDataSourceMethod() {
        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.retryAddCharactersList()

        verify { dataSourceFactory.retry() }
        verify(exactly = 0) { dataSourceFactory.refresh() }
    }

    @Test
    fun openCharacterDetail_ShouldSendAsEvent() {
        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.event.observeForever(eventObserver)

        val characterId = 1L
        viewModel.openCharacterDetail(characterId)

        val expectedEvent = CharactersListViewEvent.OpenCharacterDetail(characterId)
        assertEquals(expectedEvent, viewModel.event.value)
        verify { eventObserver.onChanged(expectedEvent) }
    }

    inner class FakeCharactersPageDataSource(
        forceNetworkState: NetworkState
    ) : CharactersPageDataSource(
        repository = mockk(),
        scope = mockk(),
        mapper = mockk()
    ) {
        init {
            networkState.postValue(forceNetworkState)
        }
    }
}
