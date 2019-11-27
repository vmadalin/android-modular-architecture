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

package com.vmadalin.dynamicfeatures.caractersfavorites.ui.favorite.di

import androidx.lifecycle.ViewModel
import com.vmadalin.commons.ui.extensions.viewModel
import com.vmadalin.core.database.characterfavorite.CharacterFavoriteRepository
import com.vmadalin.dynamicfeatures.charactersfavorites.ui.favorite.CharactersFavoriteFragment
import com.vmadalin.dynamicfeatures.charactersfavorites.ui.favorite.CharactersFavoriteViewModel
import com.vmadalin.dynamicfeatures.charactersfavorites.ui.favorite.di.CharactersFavoriteModule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class CharactersFavoriteModuleTest {

    @MockK
    lateinit var fragment: CharactersFavoriteFragment
    lateinit var module: CharactersFavoriteModule

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun initializeCharactersFavoriteModule_ShouldSetUpCorrectly() {
        module = CharactersFavoriteModule(fragment)

        assertEquals(fragment, module.fragment)
    }

    @Test
    fun verifyProvidedCharactersFavoriteViewModel() {
        mockkStatic("com.vmadalin.commons.ui.extensions.FragmentExtensionsKt")

        every {
            fragment.viewModel(any(), any<() -> ViewModel>())
        } returns mockk<CharactersFavoriteViewModel>()

        val factoryCaptor = slot<() -> CharactersFavoriteViewModel>()
        val favoriteRepository = mockk<CharacterFavoriteRepository>(relaxed = true)
        module = CharactersFavoriteModule(fragment)
        module.providesCharactersFavoriteViewModel(favoriteRepository)

        verify {
            fragment.viewModel(factory = capture(factoryCaptor))
        }

        assertEquals(favoriteRepository, factoryCaptor.captured().characterFavoriteRepository)
    }

    @Test
    fun verifyProvidedCharactersFavoriteAdapter() {
        module = CharactersFavoriteModule(fragment)

        assertNotNull(module.providesCharactersFavoriteAdapter())
    }
}
