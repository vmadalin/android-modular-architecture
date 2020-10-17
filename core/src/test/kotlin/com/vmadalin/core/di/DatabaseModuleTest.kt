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

package com.vmadalin.core.di

import android.content.Context
import com.vmadalin.core.database.MarvelDatabase
import com.vmadalin.core.database.characterfavorite.CharacterFavoriteDao
import com.vmadalin.core.di.modules.DatabaseModule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DatabaseModuleTest {

    private lateinit var databaseModule: DatabaseModule

    @Before
    fun setUp() {
        databaseModule = DatabaseModule()
    }

    @Test
    fun verifyProvidedMarvelDatabase() {
        val context: Context = mockk()
        val marvelDatabase = databaseModule.provideMarvelDatabase(context)

        assertNotNull(marvelDatabase.characterFavoriteDao())
    }

    @Test
    fun verifyProvidedCharacterFavoriteDao() {
        val marvelDatabase: MarvelDatabase = mockk()
        val characterFavoriteDao: CharacterFavoriteDao = mockk()

        every { marvelDatabase.characterFavoriteDao() } returns characterFavoriteDao

        assertEquals(
            characterFavoriteDao,
            databaseModule.provideCharacterFavoriteDao(marvelDatabase)
        )
        verify { marvelDatabase.characterFavoriteDao() }
    }

    @Test
    fun verifyProvidedCharacterFavoriteRepository() {
        val characterFavoriteDao: CharacterFavoriteDao = mockk()
        val repository = databaseModule.provideCharacterFavoriteRepository(characterFavoriteDao)

        assertEquals(characterFavoriteDao, repository.characterFavoriteDao)
    }
}
