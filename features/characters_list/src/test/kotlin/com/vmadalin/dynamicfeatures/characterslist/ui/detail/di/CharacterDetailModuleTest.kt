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

package com.vmadalin.dynamicfeatures.characterslist.ui.detail.di

import androidx.lifecycle.ViewModel
import com.vmadalin.commons.ui.extensions.viewModel
import com.vmadalin.core.database.characterfavorite.CharacterFavoriteRepository
import com.vmadalin.core.network.repositiories.MarvelRepository
import com.vmadalin.dynamicfeatures.characterslist.ui.detail.CharacterDetailFragment
import com.vmadalin.dynamicfeatures.characterslist.ui.detail.CharacterDetailViewModel
import com.vmadalin.dynamicfeatures.characterslist.ui.detail.model.CharacterDetailMapper
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CharacterDetailModuleTest {

    @MockK
    lateinit var fragment: CharacterDetailFragment
    lateinit var module: CharacterDetailModule

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun initializeCharacterDetailModule_ShouldSetUpCorrectly() {
        module = CharacterDetailModule(fragment)

        assertEquals(fragment, module.fragment)
    }

    @Test
    fun verifyProvidedCharacterDetailViewModel() {
        mockkStatic("com.vmadalin.commons.ui.extensions.FragmentExtensionsKt")

        every {
            fragment.viewModel(any(), any<() -> ViewModel>())
        } returns mockk<CharacterDetailViewModel>()

        val factoryCaptor = slot<() -> CharacterDetailViewModel>()
        val marvelRepository = mockk<MarvelRepository>(relaxed = true)
        val favoriteRepository = mockk<CharacterFavoriteRepository>(relaxed = true)
        val mapper = mockk<CharacterDetailMapper>(relaxed = true)
        module = CharacterDetailModule(fragment)
        module.providesCharacterDetailViewModel(
            marvelRepository = marvelRepository,
            characterFavoriteRepository = favoriteRepository,
            characterDetailMapper = mapper
        )

        verify {
            fragment.viewModel(factory = capture(factoryCaptor))
        }

        factoryCaptor.captured().run {
            assertEquals(marvelRepository, this.marvelRepository)
            assertEquals(favoriteRepository, this.characterFavoriteRepository)
            assertEquals(mapper, this.characterDetailMapper)
        }
    }
}
