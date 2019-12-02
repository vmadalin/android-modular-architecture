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

package com.vmadalin.dynamicfeatures.characterslist.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.vmadalin.core.database.characterfavorite.CharacterFavoriteRepository
import com.vmadalin.core.network.repositiories.MarvelRepository
import com.vmadalin.core.network.responses.BaseResponse
import com.vmadalin.core.network.responses.CharacterResponse
import com.vmadalin.dynamicfeatures.characterslist.ui.detail.model.CharacterDetail
import com.vmadalin.dynamicfeatures.characterslist.ui.detail.model.CharacterDetailMapper
import com.vmadalin.libraries.testutils.rules.CoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterDetailViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK(relaxed = true)
    lateinit var marvelRepository: MarvelRepository
    @MockK(relaxed = true)
    lateinit var characterFavoriteRepository: CharacterFavoriteRepository
    @MockK
    lateinit var characterDetailMapper: CharacterDetailMapper
    @MockK(relaxed = true)
    lateinit var stateObserver: Observer<CharacterDetailViewState>
    @MockK(relaxed = true)
    lateinit var dataObserver: Observer<CharacterDetail>
    lateinit var viewModel: CharacterDetailViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = CharacterDetailViewModel(
            marvelRepository = marvelRepository,
            characterFavoriteRepository = characterFavoriteRepository,
            characterDetailMapper = characterDetailMapper
        )
        viewModel.state.observeForever(stateObserver)
        viewModel.data.observeForever(dataObserver)
    }

    @Test
    fun loadCharacterDetail_ShouldSetLoadingState() {
        viewModel.loadCharacterDetail(1L)

        verify { stateObserver.onChanged(CharacterDetailViewState.Loading) }
    }

    @Test
    fun loadCharacterDetail_WhenError_ShouldBeErrorState() {
        viewModel.loadCharacterDetail(1L)

        val expectedState: CharacterDetailViewState = CharacterDetailViewState.Error
        Assert.assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun loadCharacterDetail_WhenSuccess_ShouldPostDataResult() {
        val characterDetail = mockk<CharacterDetail>()
        val characterResponse = mockk<BaseResponse<CharacterResponse>>()
        coEvery { marvelRepository.getCharacter(any()) } returns characterResponse
        coEvery { characterDetailMapper.map(any()) } returns characterDetail

        val characterId = 1L
        viewModel.loadCharacterDetail(characterId)

        verify { dataObserver.onChanged(characterDetail) }
        coVerify { marvelRepository.getCharacter(characterId) }
        coVerify { characterDetailMapper.map(characterResponse) }
    }

    @Test
    fun loadCharacterDetail_NonFavourite_WhenSuccess_ShouldBeAddToFavoriteState() {
        val characterDetail = mockk<CharacterDetail>()
        coEvery { characterFavoriteRepository.getCharacterFavorite(any()) } returns null
        coEvery { marvelRepository.getCharacter(any()) } returns mockk()
        coEvery { characterDetailMapper.map(any()) } returns characterDetail

        viewModel.loadCharacterDetail(1L)

        val expectedState = CharacterDetailViewState.AddToFavorite
        Assert.assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun loadCharacterDetail_Favourite_WhenSuccess_ShouldBeAlreadyAddedToFavoriteState() {
        val characterDetail = mockk<CharacterDetail>()
        coEvery { characterFavoriteRepository.getCharacterFavorite(any()) } returns mockk()
        coEvery { marvelRepository.getCharacter(any()) } returns mockk()
        coEvery { characterDetailMapper.map(any()) } returns characterDetail

        viewModel.loadCharacterDetail(1L)

        val expectedState = CharacterDetailViewState.AlreadyAddedToFavorite
        Assert.assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun addCharacterToFavorite_WhenNotLoadedDetail_ShouldDoNothing() {
        viewModel.addCharacterToFavorite()

        coVerify(exactly = 0) {
            characterFavoriteRepository.insertCharacterFavorite(any(), any(), any())
        }
        verify(exactly = 0) { stateObserver.onChanged(any()) }
    }

    @Test
    fun addCharacterToFavorite_WhenLoadedDetail_ShouldBeAddedToFavorite() {
        val characterDetail = CharacterDetail(
            id = 1011334,
            name = "3-D Man",
            description = "",
            imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
        )
        coEvery { marvelRepository.getCharacter(any()) } returns mockk()
        coEvery { characterDetailMapper.map(any()) } returns characterDetail

        viewModel.loadCharacterDetail(1L)
        viewModel.addCharacterToFavorite()

        val expectedState = CharacterDetailViewState.AddedToFavorite
        Assert.assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
        coVerify {
            characterFavoriteRepository.insertCharacterFavorite(
                id = characterDetail.id,
                name = characterDetail.name,
                imageUrl = characterDetail.imageUrl
            )
        }
    }

    @Test
    fun dismissCharacterDetail_ShouldBeDismissState() {
        viewModel.dismissCharacterDetail()

        val expectedState = CharacterDetailViewState.Dismiss
        Assert.assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }
}
