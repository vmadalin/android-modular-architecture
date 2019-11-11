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

package com.vmadalin.core.network.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import com.vmadalin.core.BuildConfig
import com.vmadalin.core.network.repositiories.MarvelRepository
import com.vmadalin.core.network.services.MarvelService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

private const val API_PUBLIC_KEY = BuildConfig.MARVEL_API_KEY_PUBLIC

class MarvelRepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var marvelService: MarvelService
    private lateinit var marvelRepository: MarvelRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        marvelRepository = MarvelRepository(marvelService)
    }

    @Test
    fun getCharacters() = runBlocking {
        val charactersOffset = 0
        val charactersLimit = 20
        val (apiKey, hash, timestamp, offset, limit) =
            argumentCaptor<String, String, String, Int, Int>()

        marvelRepository.getCharacters(
            offset = charactersOffset,
            limit = charactersLimit
        )

        verify(marvelService).getCharacters(
            apiKey = apiKey.capture(),
            hash = hash.capture(),
            timestamp = timestamp.capture(),
            offset = offset.capture(),
            limit = limit.capture()
        )

        assertEquals(API_PUBLIC_KEY, apiKey.lastValue)
        assertEquals(charactersOffset, offset.lastValue)
        assertEquals(charactersLimit, limit.lastValue)
        assertNotNull(hash.lastValue)
        assertNotNull(timestamp.lastValue)
    }

    @Test
    fun getCharacter() = runBlocking {
        val characterId = 3L
        val (id, apiKey, hash, timestamp) = argumentCaptor<Long, String, String, String>()

        marvelRepository.getCharacter(characterId)

        verify(marvelService).getCharacter(
            id = id.capture(),
            apiKey = apiKey.capture(),
            hash = hash.capture(),
            timestamp = timestamp.capture()
        )

        assertEquals(characterId, id.lastValue)
        assertEquals(API_PUBLIC_KEY, apiKey.lastValue)
        assertNotNull(hash.lastValue)
        assertNotNull(timestamp.lastValue)
    }
}
