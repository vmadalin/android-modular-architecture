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

package com.vmadalin.core.database.charactersfavorite

import com.vmadalin.core.database.characterfavorite.CharacterFavorite
import com.vmadalin.core.database.characterfavorite.CharacterFavoriteDao
import com.vmadalin.core.database.characterfavorite.CharacterFavoriteRepository
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CharacterFavoriteRepositoryTest {

    @MockK(relaxed = true)
    lateinit var characterFavoriteDao: CharacterFavoriteDao
    lateinit var characterFavoriteRepository: CharacterFavoriteRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        characterFavoriteRepository = CharacterFavoriteRepository(characterFavoriteDao)
    }

    @Test
    fun getAllCharactersFavoriteLiveData_ShouldInvokeCorrectDaoMethod() {
        characterFavoriteRepository.getAllCharactersFavoriteLiveData()

        verify { characterFavoriteDao.getAllCharactersFavoriteLiveData() }
    }

    @Test
    fun getAllCharactersFavorite_ShouldInvokeCorrectDaoMethod() {
        runBlocking {
            characterFavoriteRepository.getAllCharactersFavorite()

            coVerify { characterFavoriteDao.getAllCharactersFavorite() }
        }
    }

    @Test
    fun getCharacterFavorite_ShouldInvokeCorrectDaoMethod() = runBlocking {
        val characterIdToFind = 1L
        val characterIdCaptor = slot<Long>()
        characterFavoriteRepository.getCharacterFavorite(characterIdToFind)

        coVerify {
            characterFavoriteDao.getCharacterFavorite(capture(characterIdCaptor))
        }
        assertEquals(characterIdToFind, characterIdCaptor.captured)
    }

    @Test
    fun deleteAllCharactersFavorite_ShouldInvokeCorrectDaoMethod() = runBlocking {
        characterFavoriteRepository.deleteAllCharactersFavorite()

        coVerify { characterFavoriteDao.deleteAllCharactersFavorite() }
    }

    @Test
    fun deleteCharacterFavoriteById_ShouldInvokeCorrectDaoMethod() = runBlocking {
        val characterIdToDelete = 1L
        val characterIdCaptor = slot<Long>()
        characterFavoriteRepository.deleteCharacterFavoriteById(characterIdToDelete)

        coVerify {
            characterFavoriteDao.deleteCharacterFavoriteById(capture(characterIdCaptor))
        }
        assertEquals(characterIdToDelete, characterIdCaptor.captured)
    }

    @Test
    fun deleteCharacterFavorite_ShouldInvokeCorrectDaoMethod() = runBlocking {
        val characterToDelete = CharacterFavorite(
            0,
            "A.I.M",
            "http://i.annihil.us/535fecbbb9784.jpg"
        )
        val characterFavoriteCaptor = slot<CharacterFavorite>()
        characterFavoriteRepository.deleteCharacterFavorite(characterToDelete)

        coVerify {
            characterFavoriteDao.deleteCharacterFavorite(capture(characterFavoriteCaptor))
        }
        assertEquals(characterToDelete, characterFavoriteCaptor.captured)
    }

    @Test
    fun insertCharactersFavorites_ShouldInvokeCorrectDaoMethod() = runBlocking {
        val charactersToInsert = listOf(
            CharacterFavorite(0, "3-D Man", "http://i.annihil.us/535fecbbb9784.jpg"),
            CharacterFavorite(1, "A-Bomb (HAS)", "http://i.annihil.us/5232158de5b16.jpg"),
            CharacterFavorite(2, "A.I.M", "http://i.annihil.us/52602f21f29ec.jpg")
        )
        val charactersInsertedCaptor = slot<List<CharacterFavorite>>()
        characterFavoriteRepository.insertCharactersFavorites(charactersToInsert)

        coVerify {
            characterFavoriteDao.insertCharactersFavorites(capture(charactersInsertedCaptor))
        }
        assertEquals(charactersToInsert, charactersInsertedCaptor.captured)
    }

    @Test
    fun insertCharacterFavorite_ShouldInvokeCorrectDaoMethod() = runBlocking {
        val characterToInsert = CharacterFavorite(
            0,
            "A.I.M",
            "http://i.annihil.us/535fecbbb9784.jpg"
        )

        val characterCaptor = slot<CharacterFavorite>()
        characterFavoriteRepository.insertCharacterFavorite(
            id = characterToInsert.id,
            name = characterToInsert.name,
            imageUrl = characterToInsert.imageUrl
        )

        coVerify { characterFavoriteDao.insertCharacterFavorite(capture(characterCaptor)) }
        assertEquals(characterToInsert, characterCaptor.captured)
    }
}
