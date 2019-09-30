package com.vmadalin.core.database.characterfavorite

import androidx.lifecycle.LiveData
import javax.inject.Inject

class CharacterFavoriteRepository @Inject constructor(
    private val characterFavoriteDao: CharacterFavoriteDao
) {

    fun getCharacterFavorite(characterFavoriteId: Long): LiveData<CharacterFavorite> {
        return characterFavoriteDao.getCharacterFavorite(characterFavoriteId)
    }

    fun getAllCharactersFavorite(): LiveData<List<CharacterFavorite>> {
        return characterFavoriteDao.getAllCharactersFavorite()
    }

    suspend fun deleteAllCharactersFavorite() {
        characterFavoriteDao.deleteAllCharactersFavorite()
    }

    suspend fun deleteCharacterFavoriteById(characterFavoriteId: Long) {
        characterFavoriteDao.deleteCharacterFavoriteById(characterFavoriteId)
    }

    suspend fun deleteCharacterFavorite(character: CharacterFavorite) {
        characterFavoriteDao.deleteCharacterFavorite(character)
    }

    suspend fun insertCharacterFavorite(id: Long, name: String, imageUrl: String) {
        val characterFavorite = CharacterFavorite(
            id = id,
            name = name,
            imageUrl = imageUrl
        )
        characterFavoriteDao.insertCharacterFavorite(characterFavorite)
    }
}
