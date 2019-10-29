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

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * The data access object for the [CharacterFavorite] class.
 */
@Dao
interface CharacterFavoriteDao {

    @Query("SELECT * FROM character_favorite ORDER BY name")
    fun getAllCharactersFavoriteLiveData(): LiveData<List<CharacterFavorite>>

    @Query("SELECT * FROM character_favorite ORDER BY name")
    suspend fun getAllCharactersFavorite(): List<CharacterFavorite>

    @Query("SELECT * FROM character_favorite WHERE id = :characterFavoriteId")
    suspend fun getCharacterFavorite(characterFavoriteId: Long): CharacterFavorite

    @Query("DELETE FROM character_favorite")
    suspend fun deleteAllCharactersFavorite()

    @Query("DELETE FROM character_favorite WHERE id = :characterFavoriteId")
    suspend fun deleteCharacterFavoriteById(characterFavoriteId: Long)

    @Delete
    suspend fun deleteCharacterFavorite(character: CharacterFavorite)

    @Insert
    suspend fun insertCharacterFavorite(character: CharacterFavorite)
}
