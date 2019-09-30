package com.vmadalin.core.database.characterfavorite

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CharacterFavoriteDao {

    @Query("SELECT * FROM character_favorite")
    fun getAllCharactersFavorite(): LiveData<List<CharacterFavorite>>

    @Query("SELECT * FROM character_favorite WHERE id = :characterFavoriteId")
    fun getCharacterFavorite(characterFavoriteId: Long): LiveData<CharacterFavorite>

    @Query("DELETE FROM character_favorite")
    suspend fun deleteAllCharactersFavorite()

    @Query("DELETE FROM character_favorite WHERE id = :characterFavoriteId")
    suspend fun deleteCharacterFavoriteById(characterFavoriteId: Long)

    @Delete
    suspend fun deleteCharacterFavorite(character: CharacterFavorite)

    @Insert
    suspend fun insertCharacterFavorite(character: CharacterFavorite)

}
