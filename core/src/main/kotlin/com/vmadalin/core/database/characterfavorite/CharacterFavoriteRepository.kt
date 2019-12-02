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

/**
 * Repository module for handling character favorite data operations [CharacterFavoriteDao].
 */
class CharacterFavoriteRepository @Inject constructor(
    @VisibleForTesting(otherwise = PRIVATE)
    internal val characterFavoriteDao: CharacterFavoriteDao
) {

    /**
     * Obtain all database added favorite characters ordering by name field.
     *
     * @return [LiveData] List with favorite characters.
     */
    fun getAllCharactersFavoriteLiveData(): LiveData<List<CharacterFavorite>> =
        characterFavoriteDao.getAllCharactersFavoriteLiveData()

    /**
     * Obtain all database added favorite characters ordering by name field.
     *
     * @return List with favorite characters.
     */
    suspend fun getAllCharactersFavorite(): List<CharacterFavorite> =
        characterFavoriteDao.getAllCharactersFavorite()

    /**
     * Obtain database favorite character by identifier.
     *
     * @param characterFavoriteId Character identifier.
     *
     * @return Favorite character if exist, otherwise null
     */
    suspend fun getCharacterFavorite(characterFavoriteId: Long): CharacterFavorite? =
        characterFavoriteDao.getCharacterFavorite(characterFavoriteId)

    /**
     * Delete all database favorite characters.
     */
    suspend fun deleteAllCharactersFavorite() =
        characterFavoriteDao.deleteAllCharactersFavorite()

    /**
     * Delete database favorite character by identifier.
     *
     * @param characterFavoriteId Character identifier.
     */
    suspend fun deleteCharacterFavoriteById(characterFavoriteId: Long) =
        characterFavoriteDao.deleteCharacterFavoriteById(characterFavoriteId)

    /**
     * Delete database favorite character.
     *
     * @param character Character favorite.
     */
    suspend fun deleteCharacterFavorite(character: CharacterFavorite) =
        characterFavoriteDao.deleteCharacterFavorite(character)

    /**
     * Add to database a list of favorite characters.
     *
     * @param characters List of favorite characters.
     */
    suspend fun insertCharactersFavorites(characters: List<CharacterFavorite>) =
        characterFavoriteDao.insertCharactersFavorites(characters)

    /**
     * Add to database a favorite character.
     *
     * @param id Character identifier.
     * @param name Character name.
     * @param imageUrl Character thumbnail url.
     */
    suspend fun insertCharacterFavorite(id: Long, name: String, imageUrl: String) {
        val characterFavorite = CharacterFavorite(
            id = id,
            name = name,
            imageUrl = imageUrl
        )
        characterFavoriteDao.insertCharacterFavorite(characterFavorite)
    }
}
