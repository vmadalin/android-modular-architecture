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

package com.vmadalin.core.database.characterfavorite

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.LiveData
import javax.inject.Inject

class CharacterFavoriteRepository @Inject constructor(
    @VisibleForTesting(otherwise = PRIVATE)
    internal val characterFavoriteDao: CharacterFavoriteDao
) {

    fun getAllCharactersFavoriteLiveData(): LiveData<List<CharacterFavorite>> =
        characterFavoriteDao.getAllCharactersFavoriteLiveData()

    suspend fun getAllCharactersFavorite(): List<CharacterFavorite> =
        characterFavoriteDao.getAllCharactersFavorite()

    suspend fun getCharacterFavorite(characterFavoriteId: Long): CharacterFavorite? =
        characterFavoriteDao.getCharacterFavorite(characterFavoriteId)

    suspend fun deleteAllCharactersFavorite() =
        characterFavoriteDao.deleteAllCharactersFavorite()

    suspend fun deleteCharacterFavoriteById(characterFavoriteId: Long) =
        characterFavoriteDao.deleteCharacterFavoriteById(characterFavoriteId)

    suspend fun deleteCharacterFavorite(character: CharacterFavorite) =
        characterFavoriteDao.deleteCharacterFavorite(character)

    suspend fun insertCharactersFavorites(characters: List<CharacterFavorite>) =
        characterFavoriteDao.insertCharactersFavorites(characters)

    suspend fun insertCharacterFavorite(id: Long, name: String, imageUrl: String) {
        val characterFavorite = CharacterFavorite(
            id = id,
            name = name,
            imageUrl = imageUrl
        )
        characterFavoriteDao.insertCharacterFavorite(characterFavorite)
    }
}
